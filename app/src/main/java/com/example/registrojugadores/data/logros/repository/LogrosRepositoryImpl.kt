package com.example.registrojugadores.data.logros.repository

import com.example.registrojugadores.data.logros.local.LogroDao
import com.example.registrojugadores.data.logros.mapper.toDomain
import com.example.registrojugadores.data.logros.mapper.toEntity
import javax.inject.Inject
import com.example.registrojugadores.domain.logros.model.Logro
import com.example.registrojugadores.domain.logros.repository.LogroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LogrosRepositoryImpl @Inject constructor(
    private val dao: LogroDao
): LogroRepository {
    override fun observeLogro(): Flow<List<Logro>> = dao.ObserveAll().map {
        list -> list.map {it.toDomain() }
    }

    override suspend fun getLogro(id: Int?): Logro? = dao.getById(id)?.toDomain()

    override suspend fun upsert(logro: Logro): Int {
        dao.upsert(logro.toEntity())
        return logro.logroId
    }

    override suspend fun delete(logro: Logro) {
        dao.delete(logro.toEntity())
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun existeTitulo(titulo: String, excludeId: Int?): Boolean {
        return dao.existeTitulo(titulo, excludeId)
    }
}