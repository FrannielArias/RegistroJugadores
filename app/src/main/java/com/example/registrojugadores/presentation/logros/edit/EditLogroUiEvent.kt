package com.example.registrojugadores.presentation.logros.edit

import com.example.registrojugadores.presentation.jugador.edit.EditJugadorUiEvent

sealed interface EditLogroUiEvent {
    data class Load(val id: Int?) : EditLogroUiEvent

    data class TituloChanged(val value: String) : EditLogroUiEvent

    data class DescripcionChanged(val value: String) : EditLogroUiEvent

    data object Save : EditLogroUiEvent

    data object Delete : EditLogroUiEvent
}