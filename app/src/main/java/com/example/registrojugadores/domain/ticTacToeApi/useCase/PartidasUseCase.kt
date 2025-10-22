package com.example.registrojugadores.domain.ticTacToeApi.useCase

import com.example.registrojugadores.data.remote.dto.PartidaDto
import com.example.registrojugadores.domain.ticTacToeApi.repository.PartidasRepository

class CreatePartidaUseCase(private val repo: PartidasRepository) {
    suspend operator fun invoke(jugador1Id: Int, jugador2Id: Int): PartidaDto =
        repo.create(jugador1Id, jugador2Id)
}

class EnsurePartidaUseCase(private val repo: PartidasRepository) {
    suspend operator fun invoke(id: Int, jugador1Id: Int, jugador2Id: Int): PartidaDto {
        return if (repo.exists(id)) repo.get(id) else repo.create(jugador1Id, jugador2Id)
    }
}

class GetPartidaUseCase(private val repo: PartidasRepository) {
    suspend operator fun invoke(id: Int): PartidaDto = repo.get(id)
}

class ListPartidasUseCase(private val repo: PartidasRepository) {
    suspend operator fun invoke(): List<PartidaDto> = repo.list()
}

data class PartidasUseCases(
    val create: CreatePartidaUseCase,
    val ensure: EnsurePartidaUseCase,
    val get: GetPartidaUseCase,
    val list: ListPartidasUseCase
)
