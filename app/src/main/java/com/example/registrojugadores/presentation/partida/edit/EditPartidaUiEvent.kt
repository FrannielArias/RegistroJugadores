package com.example.registrojugadores.presentation.partida.edit


import com.example.registrojugadores.presentation.partida.edit.EditPartidaUiEvent

interface EditPartidaUiEvent {
    data class Load(val id:Int?) : EditPartidaUiEvent
    data class FechaChanged(val value: String) : EditPartidaUiEvent
    data class Jugador1Changed(val value: Int) : EditPartidaUiEvent
    data class Jugador2Changed(val value: Int) : EditPartidaUiEvent
    data class GanadorChanged(val value: Int) : EditPartidaUiEvent
    data class EsFinalizadaChanged(val value: Boolean) : EditPartidaUiEvent

    data object Save: EditPartidaUiEvent
    data object Delete: EditPartidaUiEvent
    data object Cancel: EditPartidaUiEvent
}