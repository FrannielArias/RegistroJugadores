package com.example.registrojugadores.domain.jugador.usecase

import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import javax.inject.Inject

class ExisteNombreUseCase @Inject constructor(
    private val repository: JugadorRepository
){
    suspend operator fun invoke(nombre: String, excludeId: Int? = null): Boolean {
        return repository.existeNombre(nombre, excludeId)
    }
}