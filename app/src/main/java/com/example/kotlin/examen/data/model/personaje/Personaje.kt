package com.example.kotlin.examen.data.model.personaje

import com.example.kotlin.examen.data.model.PersonajeBase

data class Personaje(
    val items: List<PersonajeBase>,
    val links: Links,
    val meta: Meta
)