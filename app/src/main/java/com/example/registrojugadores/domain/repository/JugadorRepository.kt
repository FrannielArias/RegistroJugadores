package com.example.registrojugadores.domain.repository

import com.example.registrojugadores.domain.jugador.model.Jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    suspend fun save(jugador: Jugador): Boolean
    suspend fun find(id: Int): Jugador?
    suspend fun delete(jugador: Jugador)
    suspend fun getAll(): List<Jugador>
    fun getAllFlow(): Flow<List<Jugador>>
}