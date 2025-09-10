package com.example.registrojugadores.data.repository.jugador

import com.example.registrojugadores.data.local.dao.JugadorDao
import com.example.registrojugadores.data.mapper.jugador.toDomain
import com.example.registrojugadores.data.mapper.jugador.toEntity
import com.example.registrojugadores.domain.jugador.model.Jugador
import com.example.registrojugadores.domain.jugador.repository.JugadorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val dao: JugadorDao
) : JugadorRepository {

    override fun observeJugador(): Flow<List<Jugador>> = dao.observeALL().map {
        list -> list.map { it.toDomain() }
    }

    override suspend fun getJugador(id: Int?): Jugador? = dao.getById(id)?.toDomain()

    override suspend fun upsert(jugador: Jugador): Int {
        dao.upsert(jugador.toEntity())
        return jugador.jugadorId
    }

    override suspend fun delete(judador: Jugador) {
        dao.delete(judador.toEntity())
    }
    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun existeNombre(nombre: String, excludeId: Int?): Boolean {
        return dao.existeNombre(nombre, excludeId)
    }
}