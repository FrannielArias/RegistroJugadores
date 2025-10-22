package com.example.registrojugadores.domain.ticTacToeApi.useCase

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import com.example.registrojugadores.domain.ticTacToeApi.repository.JugadorApiRepository
import kotlinx.coroutines.flow.Flow

class ObtenerJugadoresUseCase(
    private val repository: JugadorApiRepository
) {
    operator fun invoke(): Flow<List<JugadorApi>> {
        return repository.getAllFlow()
    }
}