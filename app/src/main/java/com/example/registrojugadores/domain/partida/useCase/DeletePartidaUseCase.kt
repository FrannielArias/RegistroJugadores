package com.example.registrojugadores.domain.partida.useCase

import com.example.registrojugadores.domain.partida.repository.PartidaRepository
import javax.inject.Inject

class DeletePartidaUseCase @Inject constructor(
    private val repository: PartidaRepository
){
    suspend operator fun invoke(id: Int) = repository.deleteById(id)
}