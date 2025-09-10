package com.example.registrojugadores.domain.usecase

import com.example.registrojugadores.domain.model.Jugador
import com.example.registrojugadores.domain.repository.JugadorRepository
import javax.inject.Inject

class UpsertJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    suspend operator fun invoke(jugador: Jugador): Result<Int> {
        val nombresResult = validateNombres(jugador.Nombres)
        if (!nombresResult.isValid) {
            return Result.failure(IllegalArgumentException(nombresResult.error))
        }
        val partidasResult = validatePartidas(jugador.Partidas.toString())
        if (!partidasResult.isValid) {
            return Result.failure(IllegalArgumentException(partidasResult.error))
        }

        return runCatching { repository.upsert(jugador) }
    }
}