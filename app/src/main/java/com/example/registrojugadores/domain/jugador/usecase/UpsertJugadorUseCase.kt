package com.example.registrojugadores.domain.jugador.usecase

import com.example.registrojugadores.domain.jugador.model.Jugador
import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import javax.inject.Inject

class UpsertJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(jugador: Jugador): Result<Int> {
        val nombresResult = validateNombres(jugador.nombres)
        val partidasResult = validatePartidas(jugador.partidas.toString())

        if (!nombresResult.isValid) {
            return Result.failure(IllegalArgumentException(nombresResult.error))
        }
        if (!partidasResult.isValid) {
            return Result.failure(IllegalArgumentException(partidasResult.error))
        }

        return runCatching { repository.upsert(jugador) }
    }
}