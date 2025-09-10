package com.example.registrojugadores.di

import com.example.registrojugadores.data.repository.jugador.JugadorRepositoryImpl
import com.example.registrojugadores.data.repository.partida.PartidaRepositoryImpl
import com.example.registrojugadores.domain.jugador.repository.JugadorRepository  // <- Import faltante
import com.example.registrojugadores.domain.partida.repository.PartidaRepository
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

    @Binds
    @Singleton
    abstract fun bindPartidaRepository(
        partidaRepositoryImpl: PartidaRepositoryImpl
    ): PartidaRepository
}