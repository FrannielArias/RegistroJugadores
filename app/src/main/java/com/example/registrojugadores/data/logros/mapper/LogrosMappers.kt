package com.example.registrojugadores.data.logros.mapper

import com.example.registrojugadores.data.logros.local.LogroEntity
import com.example.registrojugadores.domain.logros.model.Logro

fun LogroEntity.toDomain(): Logro = Logro(
    logroId = logroId,
    titulo = titulo,
    descripcion = descripcion
)

fun Logro.toEntity(): LogroEntity = LogroEntity(
    logroId = logroId,
    titulo = titulo,
    descripcion = descripcion
)