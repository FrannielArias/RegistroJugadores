package com.example.registrojugadores.domain.logros.useCase

import com.example.registrojugadores.domain.logros.repository.LogroRepository
import javax.inject.Inject

class ExisteTituloUseCase @Inject constructor(
    private val repository: LogroRepository
) {
    suspend operator fun invoke(titulo: String, exclusiveId: Int? = null): Boolean {
        return repository.existeTitulo(titulo, exclusiveId)
    }

}