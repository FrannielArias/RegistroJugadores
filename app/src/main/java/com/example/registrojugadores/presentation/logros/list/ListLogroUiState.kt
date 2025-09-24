package com.example.registrojugadores.presentation.logros.list

import com.example.registrojugadores.domain.logros.model.Logro
import com.example.registrojugadores.presentation.navigation.Screen

data class ListLogroUiState (
    val isLoading: Boolean = false,
    val logros: List<Logro> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)