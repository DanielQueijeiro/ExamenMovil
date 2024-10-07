package com.example.kotlin.examen.data.network

import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.data.model.PersonajeObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonajeAPIService {

    //https://dragonball-api.com/api/characters/
    @GET("characters")
    suspend fun getPersonajeList(
        @Query("page") page: Int
    ): PersonajeObject

    //https://dragonball-api.com/api/characters/1
    @GET("characters")
    suspend fun getPersonajeImage(
        @Query("page") page: Int
    ): PersonajeBase
}
