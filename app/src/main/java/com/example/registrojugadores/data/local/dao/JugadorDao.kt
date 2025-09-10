package com.example.registrojugadores.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.registrojugadores.data.local.entities.JugadorEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao {
    @Query(value= "SELECT * FROM jugadores ORDER BY JugadorId DESC")
    fun observeALL(): Flow<List<JugadorEntity>>

    @Query(value= "SELECT * FROM jugadores WHERE JugadorId = :id")
    suspend fun getById(id: Int?): JugadorEntity?

    @Upsert
    suspend fun upsert(entity: JugadorEntity)

    @Delete
    suspend fun delete(entity: JugadorEntity)

    @Query(value= "DELETE FROM jugadores WHERE JugadorId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM Jugadores WHERE LOWER(TRIM(nombres)) = LOWER(TRIM(:nombre)) AND (:excludeId IS NULL OR jugadorId != :excludeId))")
    suspend fun existeNombre(nombre: String, excludeId: Int?): Boolean
}

