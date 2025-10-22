package com.example.registrojugadores.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registrojugadores.data.jugadores.local.JugadorDao
import com.example.registrojugadores.data.partidas.local.PartidaDao
import com.example.registrojugadores.data.jugadores.local.JugadorEntity
import com.example.registrojugadores.data.logros.local.LogroDao
import com.example.registrojugadores.data.logros.local.LogroEntity
import com.example.registrojugadores.data.partidas.local.PartidaEntity
import com.example.registrojugadores.data.ticTacToeApi.Local.JugadorApiDao
import com.example.registrojugadores.data.ticTacToeApi.Local.JugadorApiEntity

@Database(
    entities = [
        JugadorEntity::class,
        PartidaEntity::class,
        LogroEntity::class,
        JugadorApiEntity::class
    ],
    version = 6,
    exportSchema = false
)
abstract class JugadorDb : RoomDatabase(){
    abstract fun jugadorDao(): JugadorDao
    abstract fun partidaDao(): PartidaDao
    abstract fun logroDao(): LogroDao
    abstract fun JugadorApiDaoDao(): JugadorApiDao
}