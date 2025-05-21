package com.bkaancelen.rickverse.data.remote.api

import com.bkaancelen.rickverse.data.remote.dto.CharacterDto
import com.bkaancelen.rickverse.data.remote.dto.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MultiverseApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null,
        @Query("page") page: Int = 1
    ): CharactersListDto

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}