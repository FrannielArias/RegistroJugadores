package com.example.registrojugadores.domain.logros.useCase

import com.example.registrojugadores.domain.logros.model.Logro
import com.example.registrojugadores.domain.logros.repository.LogroRepository
import javax.inject.Inject

class UpsertLogroUseCase @Inject constructor(
    private val repository: LogroRepository
){
    suspend operator fun invoke(logro: Logro): Result<Int>{
        val tituloResult = validateTitulo(logro.titulo)
        val descripcionResult = validateDescripcion(logro.descripcion)

        if (!tituloResult.isValid)
            return Result.failure(IllegalArgumentException(tituloResult.error))
        if (!descripcionResult.isValid)
            return Result.failure(IllegalArgumentException(descripcionResult.error))

        return runCatching { repository.upsert(logro) }
    }
}