package com.example.registrojugadores.domain.usecase

import com.example.registrojugadores.domain.repository.JugadorRepository
import com.example.registrojugadores.domain.model.Jugador
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(): Flow<List<Jugador>> = repository.observeJugadores()
}