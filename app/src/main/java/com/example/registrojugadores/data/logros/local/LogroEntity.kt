package com.example.registrojugadores.data.logros.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Logros")
data class LogroEntity (
    @PrimaryKey(autoGenerate = true)
    val logroId: Int = 0,
    val titulo: String = "",
    val descripcion: String = ""
)