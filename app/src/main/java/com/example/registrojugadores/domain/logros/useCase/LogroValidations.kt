package com.example.registrojugadores.domain.logros.useCase


data class LogroValidations(
    val isValid: Boolean,
    val error: String? = null
)
fun validateTitulo(value: String): LogroValidations{
    if (value.isBlank())
        return LogroValidations(false, "El titulo del logro es obligatorio")
    if (value.length < 3)
        return LogroValidations(false, "El titulo debe tener al menos 3 caracteres")
    return LogroValidations(true)
}

fun validateDescripcion(value: String): LogroValidations{
    if(value.isBlank())
        return LogroValidations(false, "La descripcion es obligatoria")
    if(value.length < 10)
        return LogroValidations(false, "Minimo 10 caracteres")
    return LogroValidations(true)
}
