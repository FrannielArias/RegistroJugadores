package com.example.registrojugadores.presentation.partida.list

import com.example.registrojugadores.domain.jugador.model.Jugador
import com.example.registrojugadores.domain.partida.model.Partida
import com.example.registrojugadores.presentation.navigation.Screen
import com.example.registrojugadores.presentation.partida.edit.EditPartidaUiEvent

data class ListPartidaUiState(
    val isLoading: Boolean = false,
    val partidas: List<Partida> = emptyList(),
    val jugadores: List<Jugador> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)
