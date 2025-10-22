package com.example.registrojugadores.data.ticTacToeApi.repository

import com.example.registrojugadores.data.remote.TicTacToeApi
import com.example.registrojugadores.data.remote.dto.PartidaDto
import com.example.registrojugadores.data.remote.dto.PartidaPostDto
import com.example.registrojugadores.domain.ticTacToeApi.repository.PartidasRepository
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.text.get

class PartidasRepositoryImpl @Inject constructor(
    private val api: TicTacToeApi
) : PartidasRepository {
    override suspend fun exists(id: Int): Boolean = try {
        api.getPartida(id)
        true
    } catch (e: HttpException) {
        if (e.code() == 404) false else throw e
    }

    override suspend fun get(id: Int): PartidaDto = api.getPartida(id)

    override suspend fun list(): List<PartidaDto> = api.getPartidas()

    override suspend fun create(jugador1Id: Int, jugador2Id: Int): PartidaDto {
        val resp = api.crearPartida(PartidaPostDto(jugador1Id, jugador2Id))
        if (!resp.isSuccessful) {
            throw IllegalStateException("HTTP ${resp.code()} ${resp.message()}")
        }
        val location = resp.headers()["Location"]
            ?: throw IllegalStateException("Falta header Location en respuesta 201")

        val id = location.substringAfterLast('/').toIntOrNull()
            ?: throw IllegalStateException("No se pudo parsear id desde Location=$location")


        return api.getPartida(id)
    }
}