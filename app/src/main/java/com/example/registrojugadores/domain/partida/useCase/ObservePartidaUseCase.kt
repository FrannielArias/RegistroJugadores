package com.example.registrojugadores.domain.partida.useCase

import com.example.registrojugadores.domain.partida.model.Partida
import com.example.registrojugadores.domain.partida.repository.PartidaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePartidaUseCase @Inject constructor (
    private val repository: PartidaRepository
){
    operator fun invoke(): Flow<List<Partida>> = repository.observePartida()
}