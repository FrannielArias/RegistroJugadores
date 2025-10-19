package com.example.registrojugadores.domain.jugador.repository

import com.example.registrojugadores.domain.jugador.model.Jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun observeJugador(): Flow<List<Jugador>>
    suspend fun getJugador(id: Int?): Jugador?
    suspend fun upsert(jugador: Jugador): Int
    suspend fun delete(jugador: Jugador)
    suspend fun existeNombre(nombre: String, excludeId: Int? = null): Boolean
    suspend fun deleteById(id: Int)

}