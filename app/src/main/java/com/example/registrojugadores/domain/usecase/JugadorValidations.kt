package com.example.registrojugadores.domain.usecase

data class JugadorValidations(
    val isValid: Boolean,
    val error: String? = null
)

fun validateNombres(value: String): JugadorValidations {
    if (value.isBlank()) return JugadorValidations(false, "El nombre del jugador es obligatorio")
    if (value.length < 3) return JugadorValidations(false, "El nombre debe tener al menos 3 caracteres")
    return JugadorValidations(true)
}

fun validatePartidas(value: String): JugadorValidations {
    if (value.isBlank()) return JugadorValidations(false, "El número de partidas es obligatorio")
    val number = value.toIntOrNull()
        ?: return JugadorValidations(false, "Ingrese un número válido para las partidas o un numero entero")
    if (number < 0) return JugadorValidations(false, "El número de partidas debe ser mayor o igual a cero")
    return JugadorValidations(true)
}