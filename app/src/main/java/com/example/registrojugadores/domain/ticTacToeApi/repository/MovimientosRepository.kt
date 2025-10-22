package com.example.registrojugadores.domain.ticTacToeApi.repository

import com.example.registrojugadores.data.remote.dto.MovimientoDto
import com.example.registrojugadores.data.remote.dto.MovimientoPostDto

interface MovimientosRepository {
    suspend fun getByPartida(partidaId: Int): List<MovimientoDto>
    suspend fun post(move: MovimientoPostDto)
}