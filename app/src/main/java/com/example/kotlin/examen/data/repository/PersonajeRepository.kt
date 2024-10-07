package com.example.kotlin.examen.data.repository

import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.data.network.PersonajeApiClient
import com.example.kotlin.examen.data.model.PersonajeObject    import android.util.Log


class PersonajeRepository() {
    private val apiPersonaje = PersonajeApiClient()



    suspend fun getPersonajeList(page: Int): PersonajeObject? {
        val result = apiPersonaje.getPersonajeList(page)
        Log.d("PersonajeRepository", "getPersonajeList result: $result")
        return result
    }
    suspend fun getPersonajeImage(page:Int): PersonajeBase?  = apiPersonaje.getPersonajeImage(page)

}