package com.example.registrojugadores.domain.ticTacToeApi.useCase

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import com.example.registrojugadores.domain.ticTacToeApi.repository.JugadorApiRepository

data class ValidarJugadorUseCase(
    private val repository: JugadorApiRepository
){
    suspend operator fun invoke(jugadorApi: JugadorApi): Result<Unit> {

        if (jugadorApi.nombre.isBlank() || jugadorApi.partidas < 0) {
            return Result.failure(Exception("Nombre vacío o partidas negativas"))
        }

        val jugadores = repository.getAll()
        val nombreRepetido = jugadores.any {
            it.nombre.equals(jugadorApi.nombre, ignoreCase = true) &&
                    it.id != jugadorApi.id
        }
        if (nombreRepetido) {
            return Result.failure(Exception("Ya existe un jugador con ese nombre"))
        }

        return Result.success(Unit)
    }
}
