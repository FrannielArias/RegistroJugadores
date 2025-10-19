package com.example.registrojugadores.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Jugadores : Screen()

    @Serializable
    object Partidas : Screen()

    @Serializable
    object Logros : Screen()

    @Serializable
    object TicTacToe : Screen()
}