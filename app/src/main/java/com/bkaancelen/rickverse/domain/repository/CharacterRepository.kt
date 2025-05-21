package com.bkaancelen.rickverse.domain.repository

import com.bkaancelen.rickverse.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(
        name: String? = null,
        status: String? = null,
        gender: String? = null
    ): List<Character>
}