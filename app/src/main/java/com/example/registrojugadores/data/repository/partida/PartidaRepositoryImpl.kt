package com.example.registrojugadores.data.repository.partida

import com.example.registrojugadores.data.local.dao.PartidaDao
import com.example.registrojugadores.data.mapper.jugador.toEntity
import com.example.registrojugadores.data.mapper.partida.toDomain
import com.example.registrojugadores.data.mapper.partida.toEntity
import com.example.registrojugadores.domain.partida.model.Partida
import com.example.registrojugadores.domain.partida.repository.PartidaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PartidaRepositoryImpl @Inject constructor(
    private val dao: PartidaDao
) : PartidaRepository {
    override fun observePartida(): Flow<List<Partida>> = dao.observeALL().map {
            list -> list.map { it.toDomain() }
    }

    override suspend fun getPartida(id: Int?): Partida? = dao.getById(id)?.toDomain()

    override suspend fun upsert(partida: Partida): Int {
        dao.upsert(partida.toEntity())
        return partida.partidaId
    }

    override suspend fun delete(partida: Partida) {
        dao.delete(partida.toEntity())
    }
    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

}