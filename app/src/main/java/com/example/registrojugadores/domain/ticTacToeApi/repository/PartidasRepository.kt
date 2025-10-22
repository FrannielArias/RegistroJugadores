package com.example.registrojugadores.domain.ticTacToeApi.repository

import com.example.registrojugadores.data.remote.dto.PartidaDto

interface PartidasRepository {
    suspend fun exists(id: Int): Boolean
    suspend fun create(jugador1Id: Int, jugador2Id: Int): PartidaDto
    suspend fun get(id: Int): PartidaDto
    suspend fun list(): List<PartidaDto>
}