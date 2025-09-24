package com.example.registrojugadores.data.partidas.repository

import com.example.registrojugadores.data.partidas.local.PartidaDao
import com.example.registrojugadores.data.partidas.mapper.toDomain
import com.example.registrojugadores.data.partidas.mapper.toEntity
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
        val entity = partida.toEntity()

        return if (entity.partidaId == 0) {
            val newId = dao.insert(entity)
            newId.toInt()
        } else {
            dao.update(entity)
            entity.partidaId
        }
}

    override suspend fun delete(partida: Partida) {
        dao.delete(partida.toEntity())
    }
    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

}