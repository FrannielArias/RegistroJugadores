package com.example.registrojugadores.data.ticTacToeApi.mappers

import com.example.registrojugadores.data.ticTacToeApi.Local.JugadorApiEntity
import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi

fun JugadorApiEntity.toDomain() = JugadorApi(
    id = jugadorApiId,
    nombre = nombres,
    partidas = partidas
)

fun JugadorApi.toEntity() = JugadorApiEntity(
    jugadorApiId = id,
    nombres = nombre,
    partidas = partidas
)