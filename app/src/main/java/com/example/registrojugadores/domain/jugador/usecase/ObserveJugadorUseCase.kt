package com.example.registrojugadores.domain.jugador.usecase

import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import com.example.registrojugadores.domain.jugador.model.Jugador
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(): Flow<List<Jugador>> = repository.observeJugador()
}