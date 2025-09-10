package com.example.registrojugadores.data.mapper.jugador

import com.example.registrojugadores.data.local.entities.JugadorEntity
import com.example.registrojugadores.domain.jugador.model.Jugador

fun JugadorEntity.toDomain(): Jugador = Jugador(
    jugadorId = jugadorId,
    nombres = nombres,
    partidas = partidas
)

fun Jugador.toEntity(): JugadorEntity = JugadorEntity(
    jugadorId = jugadorId,
    nombres = nombres,
    partidas = partidas
)