package com.example.registrojugadores.domain.logros.useCase

import com.example.registrojugadores.domain.logros.model.Logro
import com.example.registrojugadores.domain.logros.repository.LogroRepository
import javax.inject.Inject

class GetLogroUseCase @Inject constructor(
    private val repository: LogroRepository
) {
    suspend operator fun invoke(id: Int?): Logro? = repository.getLogro(id)
}