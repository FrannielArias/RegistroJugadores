package com.example.registrojugadores.domain.usecase

import com.example.registrojugadores.domain.repository.JugadorRepository
import javax.inject.Inject

class DeleteJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
){
    suspend operator fun invoke(id: Int) = repository.delete(id)
}