package com.example.registrojugadores.data.partidas.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.registrojugadores.data.jugadores.local.JugadorEntity

@Entity(
    tableName = "partidas",
    foreignKeys = [
        ForeignKey(
            entity = JugadorEntity::class,
            parentColumns = ["jugadorId"],
            childColumns = ["jugador1Id"],
            onDelete = ForeignKey.Companion.RESTRICT
        ),
        ForeignKey(
            entity = JugadorEntity::class,
            parentColumns = ["jugadorId"],
            childColumns = ["jugador2Id"],
            onDelete = ForeignKey.Companion.RESTRICT
        ),
        ForeignKey(
            entity = JugadorEntity::class,
            parentColumns = ["jugadorId"],
            childColumns = ["ganadorId"],
            onDelete = ForeignKey.Companion.RESTRICT
        ),
    ]
)

data class PartidaEntity (
    @PrimaryKey(autoGenerate = true)
    val partidaId: Int = 0,
    val fecha: String = "",
    val jugador1Id: Int = 0,
    val jugador2Id: Int = 0,
    val ganadorId: Int = 0,
    val esFinalizada: Boolean = false
)