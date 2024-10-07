package com.example.kotlin.examen.data.network

import com.example.kotlin.examen.data.model.PersonajeObject
import com.example.kotlin.examen.data.model.PersonajeBase

class PersonajeApiClient {
    private lateinit var api: PersonajeAPIService

    suspend fun getPersonajeList(page:Int): PersonajeObject?{
        api = NetworkModuleDI()
        return try{
            api.getPersonajeList(page)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun getPersonajeImage(numeroPersonaje:Int): PersonajeBase? {
        api = NetworkModuleDI()
        return try{
            api.getPersonajeInfo(numeroPersonaje)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }
}