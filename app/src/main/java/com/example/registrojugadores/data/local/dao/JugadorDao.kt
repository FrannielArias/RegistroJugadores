package com.example.registrojugadores.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.registrojugadores.data.local.entities.JugadorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao {
    @Query("SELECT * FROM jugadores ORDER BY jugadorId DESC")
    fun observeALL(): Flow<List<JugadorEntity>>

    @Query("SELECT * FROM jugadores WHERE jugadorId = :id")
    suspend fun getById(id: Int?): JugadorEntity?

    @Upsert
    suspend fun upsert(entity: JugadorEntity)

    @Delete
    suspend fun delete(entity: JugadorEntity)

    @Query("DELETE FROM jugadores WHERE jugadorId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM jugadores " +
            "WHERE LOWER(TRIM(nombres)) = LOWER(TRIM(:nombre)) " +
            "AND (:excludeId IS NULL OR jugadorId != :excludeId))")
    suspend fun existeNombre(nombre: String, excludeId: Int?): Boolean
}

