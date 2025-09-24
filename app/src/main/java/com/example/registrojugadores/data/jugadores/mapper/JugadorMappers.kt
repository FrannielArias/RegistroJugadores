package com.example.registrojugadores.data.jugadores.mapper

import com.example.registrojugadores.data.jugadores.local.JugadorEntity
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