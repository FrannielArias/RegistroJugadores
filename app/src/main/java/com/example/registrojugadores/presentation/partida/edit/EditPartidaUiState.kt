package com.example.registrojugadores.presentation.partida.edit

data class EditPartidaUiState (
    val isNew: Boolean = true,
    val partidaId: Int? = null,
    val fecha: String = "",
    val jugador1Id: Int = 0,
    val jugador2Id: Int = 0,
    val ganadorId: Int = 0,
    val esFinalizada: Boolean = false,
    val jugador1Error: String? = null,
    val jugador2Error: String? = null,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val deleted: Boolean = false
)