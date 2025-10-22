package com.example.registrojugadores.domain.ticTacToeApi.useCase

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import com.example.registrojugadores.domain.ticTacToeApi.repository.JugadorApiRepository

data class EliminarJugadorUseCase(
    private val repository: JugadorApiRepository
){
    suspend operator fun invoke(jugadorApi: JugadorApi){
        repository.delete(jugadorApi)
    }
}
