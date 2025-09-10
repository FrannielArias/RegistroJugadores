package com.example.registrojugadores.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jugadores")
data class JugadorEntity(
    @PrimaryKey
    val JugadorId: Int? = null,
    val Nombres: String = "",
    val Partidas: Int = 0,
)
