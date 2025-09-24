package com.example.registrojugadores.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registrojugadores.data.jugadores.local.JugadorDao
import com.example.registrojugadores.data.partidas.local.PartidaDao
import com.example.registrojugadores.data.jugadores.local.JugadorEntity
import com.example.registrojugadores.data.logros.local.LogroDao
import com.example.registrojugadores.data.logros.local.LogroEntity
import com.example.registrojugadores.data.partidas.local.PartidaEntity

@Database(
    entities = [
        JugadorEntity::class,
        PartidaEntity::class,
        LogroEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class JugadorDb : RoomDatabase(){
    abstract fun jugadorDao(): JugadorDao
    abstract fun partidaDao(): PartidaDao

    abstract fun logroDao(): LogroDao

}