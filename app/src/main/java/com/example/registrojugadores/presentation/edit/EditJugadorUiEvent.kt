package com.example.registrojugadores.presentation.edit

sealed interface EditJugadorUiEvent {
    data class Load(val id: Int?) : EditJugadorUiEvent

    data class NombreChanged(val value: String) : EditJugadorUiEvent

    data class PartidasChanged(val value: String) : EditJugadorUiEvent

    data object Save : EditJugadorUiEvent

    data object Delete : EditJugadorUiEvent
}