package com.example.registrojugadores.presentation.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrojugadores.data.remote.dto.PartidaDto
import com.example.registrojugadores.domain.ticTacToeApi.useCase.PartidasUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PartidasUiState(
    val loading: Boolean = false,
    val partidas: List<PartidaDto> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class PartidasViewModel @Inject constructor(
    private val useCases: PartidasUseCases
) : ViewModel() {

    private val _ui = MutableStateFlow(PartidasUiState())
    val ui: StateFlow<PartidasUiState> = _ui

    fun load() {
        viewModelScope.launch {
            try {
                _ui.value = _ui.value.copy(loading = true, error = null)
                val list = useCases.list()
                _ui.value = _ui.value.copy(loading = false, partidas = list)
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(loading = false, error = e.message ?: "Error")
            }
        }
    }

    fun crear(onCreated: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                _ui.value = _ui.value.copy(loading = true, error = null)
                val creada = useCases.create(jugador1Id = 1, jugador2Id = 2)
                val list = useCases.list()
                _ui.value = _ui.value.copy(loading = false, partidas = list)
                onCreated(creada.partidaId)
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(loading = false, error = e.message ?: "Error")
            }
        }
    }
}
