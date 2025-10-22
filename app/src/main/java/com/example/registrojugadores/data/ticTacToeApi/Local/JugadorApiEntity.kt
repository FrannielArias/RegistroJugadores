package com.example.registrojugadores.data.ticTacToeApi.Local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "jugadoresApi")
data class JugadorApiEntity (
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val jugadorApiId: Int? = null,
    val nombres: String = "",
    val partidas: Int,
    val isPendingCreate: Boolean = false
)