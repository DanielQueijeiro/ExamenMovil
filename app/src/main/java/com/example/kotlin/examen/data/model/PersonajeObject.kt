package com.example.kotlin.examen.data.model

import com.example.kotlin.examen.data.model.personaje.Links
import com.example.kotlin.examen.data.model.personaje.Meta
import com.example.kotlin.examen.data.model.personaje.Personaje
import com.google.gson.annotations.SerializedName

data class PersonajeObject(
    @SerializedName("items") val items: ArrayList<PersonajeBase>,
    @SerializedName("meta") val meta: Meta,
    @SerializedName("links") val links: Links,
)
