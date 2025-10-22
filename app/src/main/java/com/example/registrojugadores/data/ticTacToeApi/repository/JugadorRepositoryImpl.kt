package com.example.registrojugadores.data.ticTacToeApi.repository

import com.example.registrojugadores.data.ticTacToeApi.Local.JugadorApiDao
import com.example.registrojugadores.data.ticTacToeApi.Local.JugadorApiEntity
import com.example.registrojugadores.data.ticTacToeApi.mappers.toDomain
import com.example.registrojugadores.data.ticTacToeApi.mappers.toEntity
import com.example.registrojugadores.domain.ticTacToeApi.model.JugadorApi
import com.example.registrojugadores.domain.ticTacToeApi.repository.JugadorApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val dao: JugadorApiDao
) : JugadorApiRepository {

    override suspend fun save(jugadorApi: JugadorApi): Boolean {
        dao.upsert(jugadorApi.toEntity())
        return true
    }

    override suspend fun find(id: Int): JugadorApi? =
        dao.find(id)?.toDomain()

    override suspend fun delete(jugadorApi: JugadorApi) {
        dao.delete(jugadorApi.toEntity())
    }

    override suspend fun getAll(): List<JugadorApi> =
        dao.observeAll().firstOrNull()?.map { it.toDomain() } ?: emptyList()

    override fun getAllFlow(): Flow<List<JugadorApi>> =
        dao.observeAll().map { entities -> entities.map(JugadorApiEntity::toDomain) }
}