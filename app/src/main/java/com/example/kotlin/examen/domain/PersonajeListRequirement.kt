package com.example.kotlin.examen.domain

import com.example.kotlin.examen.data.model.PersonajeObject
import com.example.kotlin.examen.data.repository.PersonajeRepository


class PersonajeListRequirement {
    private val repository = PersonajeRepository()

    suspend operator fun invoke(page:Int): PersonajeObject? = repository.getPersonajeList(page)

}