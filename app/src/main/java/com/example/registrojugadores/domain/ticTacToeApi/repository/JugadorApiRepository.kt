package com.example.registrojugadores.domain.ticTacToeApi.repository

import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import kotlinx.coroutines.flow.Flow

interface JugadorApiRepository {
    suspend fun save(jugador: JugadorApi): Boolean
    suspend fun find(id: Int): JugadorApi?
    suspend fun delete(jugador: JugadorApi)
    suspend fun getAll(): List<JugadorApi>
    fun getAllFlow(): Flow<List<JugadorApi>>
}