package com.example.registrojugadores.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(
    tableName = "partidas",
    foreignKeys = [
        ForeignKey (
            entity = JugadorEntity::class,
            parentColumns = ["jugadorId"],
            childColumns = ["jugador1Id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey (
            entity = JugadorEntity::class,
            parentColumns = ["jugadorId"],
            childColumns = ["jugador2Id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey (
            entity = JugadorEntity::class,
            parentColumns = ["jugadorId"],
            childColumns = ["ganadorId"],
            onDelete = ForeignKey.RESTRICT
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
