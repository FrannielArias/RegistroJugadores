package com.example.registrojugadores.presentation.partida.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import com.example.registrojugadores.domain.partida.useCase.DeletePartidaUseCase
import com.example.registrojugadores.domain.partida.useCase.ObservePartidaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPartidaViewModel @Inject constructor(
    private val observePartidaUseCase: ObservePartidaUseCase,
    private val deletePartidaByIdUseCase: DeletePartidaUseCase,
    private val jugadorRepository: JugadorRepository
): ViewModel() {
    private val _state = MutableStateFlow(ListPartidaUiState(isLoading = true))

    val state: StateFlow<ListPartidaUiState> = _state.asStateFlow()

    init {
        onEvent(ListPartidaUiEvent.Load)
        loadJugadores()
    }

    fun onEvent(event: ListPartidaUiEvent){
        when(event){
            ListPartidaUiEvent.Load -> observe()
            is ListPartidaUiEvent.Delete -> onDelete(event.id)
            ListPartidaUiEvent.CreateNew -> _state.update { it.copy(navigationToCreate = true) }
            is ListPartidaUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
            is ListPartidaUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
        }
    }

    private fun observe(){
        viewModelScope.launch{
            observePartidaUseCase().collect{ list ->
                _state.update { it.copy(isLoading = false, partidas = list, message = null) }
            }
        }
    }

    private fun loadJugadores() {
        viewModelScope.launch {
            jugadorRepository.observeJugador().collect { jugadores ->
                _state.update { it.copy(jugadores = jugadores) }
            }
        }
    }

    private fun onDelete(id: Int){
        viewModelScope.launch {
            deletePartidaByIdUseCase(id)
            onEvent(ListPartidaUiEvent.ShowMessage("Partida $id eliminada"))
        }
    }

    fun onNavigationHandled(){
        _state.update { it.copy(navigationToCreate = false, navigateToEditId = null) }
    }
}