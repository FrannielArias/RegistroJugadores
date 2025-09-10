package com.example.registrojugadores.di

import com.example.registrojugadores.data.repository.JugadorRepositoryImpl
import com.example.registrojugadores.domain.repository.JugadorRepository  // <- Import faltante
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindJugadorRepository(
        jugadorRepositoryImpl: JugadorRepositoryImpl
    ): JugadorRepository
}