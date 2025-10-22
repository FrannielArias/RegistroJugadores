package com.example.registrojugadores.di

import android.content.Context

import androidx.room.Room
import com.example.registrojugadores.data.database.JugadorDb
import com.example.registrojugadores.data.remote.TicTacToeApi
import com.example.registrojugadores.data.ticTacToeApi.repository.MovimientosRepositoryImpl
import com.example.registrojugadores.data.ticTacToeApi.repository.PartidasRepositoryImpl
import com.example.registrojugadores.domain.ticTacToeApi.repository.MovimientosRepository
import com.example.registrojugadores.domain.ticTacToeApi.repository.PartidasRepository
import com.example.registrojugadores.domain.ticTacToeApi.useCase.CreatePartidaUseCase
import com.example.registrojugadores.domain.ticTacToeApi.useCase.EnsurePartidaUseCase
import com.example.registrojugadores.domain.ticTacToeApi.useCase.GetPartidaUseCase
import com.example.registrojugadores.domain.ticTacToeApi.useCase.ListPartidasUseCase
import com.example.registrojugadores.domain.ticTacToeApi.useCase.PartidasUseCases
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val log = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(log).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://gestionhuacalesapi.azurewebsites.net/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): TicTacToeApi =
        retrofit.create(TicTacToeApi::class.java)

    @Provides
    @Singleton
    fun provideMovimientosRepo(api: TicTacToeApi): MovimientosRepository =
        MovimientosRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePartidasRepository(api: TicTacToeApi): PartidasRepository =
        PartidasRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePartidasUseCases(repo: PartidasRepository): PartidasUseCases =
        PartidasUseCases(
            create = CreatePartidaUseCase(repo),
            ensure = EnsurePartidaUseCase(repo),
            get = GetPartidaUseCase(repo),
            list = ListPartidasUseCase(repo)
        )

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

    @Provides
    fun providePartidaDao(jugadorDb: JugadorDb) = jugadorDb.partidaDao()

    @Provides
    fun provideLogroDao(jugadorDb: JugadorDb) = jugadorDb.logroDao()
}