package com.bkaancelen.rickverse.domain.repository

import com.bkaancelen.rickverse.domain.model.CharactersList

interface CharacterRepository {
    suspend fun getCharacters(
        name: String? = null,
        status: String? = null,
        gender: String? = null,
        page: Int = 1
    ): CharactersList
}