package com.example.registrojugadores.presentation.partida.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrojugadores.domain.partida.model.Partida
import com.example.registrojugadores.domain.partida.useCase.DeletePartidaUseCase
import com.example.registrojugadores.domain.partida.useCase.GetPartidaUseCase
import com.example.registrojugadores.domain.partida.useCase.UpsertPartidaUseCase
import com.example.registrojugadores.domain.partida.useCase.validateJugador1
import com.example.registrojugadores.domain.partida.useCase.validateJugador2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditPartidaViewModel @Inject constructor(
    private val getPartidaByIdUseCase: GetPartidaUseCase,
    private val upsertPartidaUseCase: UpsertPartidaUseCase,
    private val deletePartidaUseCase: DeletePartidaUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(value = EditPartidaUiState())

    val state: StateFlow<EditPartidaUiState> = _state.asStateFlow()

    fun onEvent(event: EditPartidaUiEvent) {
        when (event) {
            is EditPartidaUiEvent.Load -> onLoad(id = event.id)
            is EditPartidaUiEvent.FechaChanged -> _state.update {
                it.copy(fecha = event.value)
            }
            is EditPartidaUiEvent.Jugador1Changed -> _state.update {
                it.copy(jugador1Id = event.value, jugador1Error = null)
            }

            is EditPartidaUiEvent.Jugador2Changed -> _state.update {
                it.copy(jugador2Id = event.value, jugador2Error = null)
            }

            is EditPartidaUiEvent.GanadorChanged -> _state.update {
                it.copy(ganadorId = event.value)
            }

            is EditPartidaUiEvent.EsFinalizadaChanged -> _state.update {
                it.copy(esFinalizada = event.value)
            }

            EditPartidaUiEvent.Delete -> onDelete()

            EditPartidaUiEvent.Save -> onSave()
        }
    }

    private fun onLoad(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, partidaId = null) }
            return
        }
        viewModelScope.launch {
            val partida = getPartidaByIdUseCase(id)
            if (partida != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        partidaId = partida.partidaId,
                        fecha = partida.fecha,
                        jugador1Id = partida.jugador1Id,
                        jugador2Id = partida.jugador2Id,
                        ganadorId = partida.ganadorId,
                        esFinalizada = partida.esFinalizada
                    )
                }
            }
        }
    }

    private fun onSave() {
        val jugador1Id = state.value.jugador1Id
        val jugador2Id = state.value.jugador2Id

        val jugador1Validations = validateJugador1(jugador1Id.toString(), jugador2Id.toString())
        val jugador2Validations = validateJugador2(jugador2Id.toString(), jugador1Id.toString())


        if (!jugador1Validations.isValid || !jugador2Validations.isValid) {
            _state.update {
                it.copy(
                    jugador1Error = jugador1Validations.error,
                    jugador2Error = jugador2Validations.error
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val id = state.value.partidaId ?: 0
            val partida = Partida(
                id,
                state.value.fecha,
                state.value.jugador1Id,
                state.value.jugador2Id,
                state.value.ganadorId,
                state.value.esFinalizada
            )
            val result = upsertPartidaUseCase(partida)
            result.onSuccess { newId ->
                _state.value = EditPartidaUiState()
            }.onFailure { e ->
                _state.update { it.copy(isSaving = false) }
            }
        }
    }

    private fun onDelete(){
        val id = state.value.partidaId ?: return
        viewModelScope.launch {
            _state.update {it.copy(isDeleting = true)}
            deletePartidaUseCase(id) // Todo Manejar resultado
            _state.update { it.copy(isDeleting = false, deleted = true)}
        }
    }
}