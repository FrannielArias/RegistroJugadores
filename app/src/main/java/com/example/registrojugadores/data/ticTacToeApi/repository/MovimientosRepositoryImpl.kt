package com.example.registrojugadores.data.ticTacToeApi.repository

import com.example.registrojugadores.data.remote.TicTacToeApi
import com.example.registrojugadores.data.remote.dto.MovimientoPostDto
import com.example.registrojugadores.data.remote.dto.MovimientoDto
import com.example.registrojugadores.domain.ticTacToeApi.repository.MovimientosRepository
import javax.inject.Inject

class MovimientosRepositoryImpl @Inject constructor(
    private val api: TicTacToeApi
) : MovimientosRepository {

    override suspend fun getByPartida(partidaId: Int): List<MovimientoDto> {
        return api.getMovimientos(partidaId)
    }

    override suspend fun post(move: MovimientoPostDto) {
        val r = api.postMovimiento(move)
        if (!r.isSuccessful) throw IllegalStateException("HTTP ${r.code()} ${r.message()}")
    }
}
