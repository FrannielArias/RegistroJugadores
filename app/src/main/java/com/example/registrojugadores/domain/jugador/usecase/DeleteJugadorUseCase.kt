package com.example.registrojugadores.domain.jugador.usecase

import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import javax.inject.Inject

class DeleteJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
){
    suspend operator fun invoke(id: Int) = repository.deleteById(id)
}