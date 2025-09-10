package com.example.registrojugadores.domain.partida.useCase

data class ValidacionesPartidas(
    val isValid: Boolean,
    val error:String? = null
)

fun validateJugador1(jugador1value:String, jugador2Value:String): ValidacionesPartidas{
    if(jugador1value.isBlank())
        return ValidacionesPartidas(false, "Debe de haber un jugador 1.")
    val jugador1 = jugador1value.toIntOrNull()
    if(jugador1 == null || jugador1 <= 0)
        return ValidacionesPartidas(false, "El jugador 1 debe de ser valido.")
    if(jugador1value == jugador2Value)
        return ValidacionesPartidas(false, "El jugador 1 no puede ser igual al jugador 2.")
    return ValidacionesPartidas(true)

}

fun validateJugador2(jugador2Value:String, jugador1Value:String): ValidacionesPartidas{
    if(jugador2Value.isBlank())
        return ValidacionesPartidas(false, "Debe de haber un jugador 2.")
    val jugador2 = jugador2Value.toIntOrNull()
    if(jugador2 == null || jugador2 <= 0)
        return ValidacionesPartidas(false, "El jugador 2 debe de ser valido.")
    if(jugador2Value == jugador1Value)
        return ValidacionesPartidas(false, "El jugador 2 no puede ser igual al jugador 1.")
    return ValidacionesPartidas(true)
}