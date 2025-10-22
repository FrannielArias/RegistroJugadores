package com.example.registrojugadores.data.remote

import com.example.registrojugadores.data.remote.dto.MovimientoDto
import com.example.registrojugadores.data.remote.dto.MovimientoPostDto
import com.example.registrojugadores.data.remote.dto.PartidaDto
import com.example.registrojugadores.data.remote.dto.PartidaPostDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TicTacToeApi {
    @GET("api/Movimientos/{partidaId}")
    suspend fun getMovimientos(@Path("partidaId") partidaId: Int): List<MovimientoDto>

    @POST("api/Movimientos")
    suspend fun postMovimiento(@Body body: MovimientoPostDto): Response<Unit>

    @GET("api/Partidas")
    suspend fun getPartidas(): List<PartidaDto>

    @GET("api/Partidas/{id}")
    suspend fun getPartida(@Path("id") id: Int): PartidaDto
    @POST("api/Partidas")
    suspend fun crearPartida(@Body body: PartidaPostDto): Response<Unit>
}