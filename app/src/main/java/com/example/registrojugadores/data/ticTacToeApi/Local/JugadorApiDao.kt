package com.example.registrojugadores.data.ticTacToeApi.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorApiDao{
    @Upsert
    suspend fun upsert(jugadorApi: JugadorApiEntity)

    @Query("SELECT * FROM jugadoresApi WHERE jugadorApiId = :id LIMIT 1")
    suspend fun find(id: Int): JugadorApiEntity?

    @Delete
    suspend fun delete(jugadorApi: JugadorApiEntity)

    @Query("SELECT * FROM jugadoresApi")
    fun observeAll(): Flow<List<JugadorApiEntity>>

    @Query("SELECT * FROM jugadoresApi WHERE isPendingCreate = 1")
    suspend fun getPendingCreate(): List<JugadorApiEntity>
}