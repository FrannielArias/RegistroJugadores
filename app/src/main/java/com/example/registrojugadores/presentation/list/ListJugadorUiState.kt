package com.example.registrojugadores.presentation.list

import com.example.registrojugadores.domain.model.Jugador

data class ListJugadorUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugador> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)
