package com.example.registrojugadores.presentation.jugadorApi

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi

interface JugadorApiEvent {
    data class JugadorChange(val id: Int) : JugadorApiEvent
    data object Delete : JugadorApiEvent
}