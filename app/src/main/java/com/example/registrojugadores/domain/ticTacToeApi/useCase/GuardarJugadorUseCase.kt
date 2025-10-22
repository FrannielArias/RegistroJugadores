package com.example.registrojugadores.domain.ticTacToeApi.useCase

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import com.example.registrojugadores.domain.ticTacToeApi.repository.JugadorApiRepository

data class GuardarJugadorUseCase(
    private val repository: JugadorApiRepository,
    private val validarJugador: ValidarJugadorUseCase
){
    suspend operator fun invoke(jugadorApi: JugadorApi): Result<Boolean> {

        val validacion = validarJugador(jugadorApi)
        if (validacion.isFailure) return Result.failure(validacion.exceptionOrNull()!!)

        val result = repository.save(jugadorApi)
        return Result.success(result)
    }
}
