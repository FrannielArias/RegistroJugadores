package com.example.registrojugadores.domain.ticTacToeApi.useCase

data class JugadorUseCase(
    val validarJugador: ValidarJugadorUseCase,
    val guardarJugador: GuardarJugadorUseCase,
    val eliminarJugador: EliminarJugadorUseCase,
    val obtenerJugador: ObtenerJugadorUseCase,
    val obtenerJugadores: ObtenerJugadoresUseCase
)
