package com.example.registrojugadores.domain.repository

import com.example.registrojugadores.domain.model.Jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun observeJugadores(): Flow<List<Jugador>>

    suspend fun getJugador(id: Int?): Jugador?

    suspend fun upsert(jugador: Jugador): Int

    suspend fun delete(id: Int)

    suspend fun existeNombre(nombre: String, excludeId: Int? = null): Boolean

    suspend fun deleteById(id: Int)

}