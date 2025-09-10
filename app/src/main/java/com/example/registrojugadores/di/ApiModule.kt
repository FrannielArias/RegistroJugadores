package com.example.registrojugadores.di

import android.content.Context

import androidx.room.Room
import com.example.registrojugadores.data.local.database.JugadorDb
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideTicketDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            JugadorDb::class.java,
            "JugadorDb"
        ).fallbackToDestructiveMigration()
            .build()
    @Provides
    fun provideJugadorDao(jugadorDb: JugadorDb) = jugadorDb.jugadorDao()
}