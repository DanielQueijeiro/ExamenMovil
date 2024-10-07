package com.example.kotlin.examen.domain

import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.data.repository.PersonajeRepository


class PersonajeInfoRequirement {

    private val repository = PersonajeRepository()

    suspend operator fun invoke(page: Int): PersonajeBase? = repository.getPersonajeImage(page)
}