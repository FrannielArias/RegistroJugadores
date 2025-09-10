package com.example.registrojugadores.domain.partida.useCase

import com.example.registrojugadores.domain.jugador.model.Jugador
import com.example.registrojugadores.domain.partida.model.Partida
import com.example.registrojugadores.domain.partida.repository.PartidaRepository
import javax.inject.Inject

class GetPartidaUseCase @Inject constructor(
    private val repository: PartidaRepository
){
    suspend operator fun invoke(id: Int?): Partida? =  repository.getPartida(id)
}