package com.example.registrojugadores.domain.logros.useCase

import com.example.registrojugadores.domain.logros.model.Logro
import com.example.registrojugadores.domain.logros.repository.LogroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLogroUseCase @Inject constructor(
    private val repository: LogroRepository
){
    operator fun invoke(): Flow<List<Logro>> = repository.observeLogro()

}