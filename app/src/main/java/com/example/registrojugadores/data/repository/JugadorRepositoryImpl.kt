package com.example.registrojugadores.data.repository

import com.example.registrojugadores.data.local.dao.JugadorDao
import com.example.registrojugadores.data.mapper.toDomain
import com.example.registrojugadores.data.mapper.toEntity
import com.example.registrojugadores.domain.model.Jugador
import com.example.registrojugadores.domain.repository.JugadorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val dao: JugadorDao
) : JugadorRepository {

    override fun observeJugadores(): Flow<List<Jugador>> = dao.observeALL().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun getJugador(id: Int?): Jugador? = dao.getById(id)?.toDomain()

    override suspend fun upsert(jugador: Jugador): Int {
        dao.upsert(jugador.toEntity())
        return jugador.JugadorId
    }

    override suspend fun delete(id: Int): Unit {
        dao.deleteById(id)
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun existeNombre(nombre: String, excludeId: Int?): Boolean {
        return dao.existeNombre(nombre, excludeId)
    }
}