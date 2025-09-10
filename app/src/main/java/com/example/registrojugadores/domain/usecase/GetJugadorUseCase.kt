package com.example.registrojugadores.domain.usecase

import com.example.registrojugadores.domain.repository.JugadorRepository
import com.example.registrojugadores.domain.model.Jugador
import javax.inject.Inject

class GetJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(id: Int?): Jugador? =  repository.getJugador(id)
}