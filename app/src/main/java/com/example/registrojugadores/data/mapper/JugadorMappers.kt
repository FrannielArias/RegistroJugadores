package com.example.registrojugadores.data.mapper

import com.example.registrojugadores.data.local.entities.JugadorEntity
import com.example.registrojugadores.domain.model.Jugador

fun JugadorEntity.toDomain(): Jugador = Jugador(
    JugadorId = JugadorId ?: 0,
    Nombres = Nombres,
    Partidas = Partidas
)

fun Jugador.toEntity(): JugadorEntity = JugadorEntity(
    JugadorId = JugadorId,
    Nombres = Nombres,
    Partidas = Partidas
)