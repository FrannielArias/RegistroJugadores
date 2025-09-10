package com.example.registrojugadores.presentation.partida.list

import com.example.registrojugadores.domain.partida.model.Partida

data class ListPartidaUiState(
    val isLoading: Boolean = false,
    val partidas: List<Partida> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)
