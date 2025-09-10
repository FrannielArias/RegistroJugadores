package com.example.registrojugadores.domain.jugador.usecase

import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import com.example.registrojugadores.domain.jugador.model.Jugador
import javax.inject.Inject

class GetJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int?): Jugador? =  repository.getJugador(id)
}