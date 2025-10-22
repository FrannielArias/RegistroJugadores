package com.example.registrojugadores.domain.ticTacToeApi.useCase

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import com.example.registrojugadores.domain.ticTacToeApi.repository.JugadorApiRepository

data class ObtenerJugadorUseCase(
    private val repository: JugadorApiRepository
){
    suspend operator fun invoke(id: Int): JugadorApi? {
        return repository.find(id)
    }
}
