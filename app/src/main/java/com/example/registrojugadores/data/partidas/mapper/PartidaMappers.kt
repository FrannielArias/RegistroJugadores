package com.example.registrojugadores.data.partidas.mapper

import com.example.registrojugadores.data.partidas.local.PartidaEntity
import com.example.registrojugadores.domain.partida.model.Partida

fun PartidaEntity.toDomain(): Partida = Partida(
    partidaId = partidaId ?: 0,
    fecha = fecha,
    jugador1Id = jugador1Id,
    jugador2Id = jugador2Id,
    ganadorId = ganadorId,
    esFinalizada = esFinalizada
)

fun Partida.toEntity(): PartidaEntity = PartidaEntity(
    partidaId = partidaId,
    fecha = fecha,
    jugador1Id = jugador1Id,
    jugador2Id = jugador2Id,
    ganadorId = ganadorId,
    esFinalizada = esFinalizada
)