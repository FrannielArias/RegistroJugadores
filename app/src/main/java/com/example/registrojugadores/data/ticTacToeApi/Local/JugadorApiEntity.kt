package com.example.registrojugadores.data.ticTacToeApi.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jugadoresApi")
data class JugadorApiEntity (
    @PrimaryKey
    val jugadorApiId: Int? = null,
    val nombres: String = "",
    val partidas: Int = 0
)