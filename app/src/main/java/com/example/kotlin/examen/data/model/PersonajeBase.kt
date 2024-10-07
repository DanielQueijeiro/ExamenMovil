package com.example.kotlin.examen.data.model

import com.google.gson.annotations.SerializedName

data class PersonajeBase(
    val affiliation: String,
    val deletedAt: Any,
    val description: String,
    val gender: String,
    val id: Int,
    val image: String,
    val ki: String,
    val maxKi: String,
    val name: String,
    val race: String
)
