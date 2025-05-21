package com.bkaancelen.rickverse.data.remote.api

import com.bkaancelen.rickverse.data.remote.dto.CharacterDto
import com.bkaancelen.rickverse.data.remote.dto.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MultiverseApi {
    @GET("character")
    suspend fun getCharacters(): CharactersListDto

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}