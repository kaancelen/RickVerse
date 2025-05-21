package com.bkaancelen.rickverse.data.repository

import com.bkaancelen.rickverse.data.mapper.toDomain
import com.bkaancelen.rickverse.data.remote.api.MultiverseApi
import com.bkaancelen.rickverse.domain.model.Character
import com.bkaancelen.rickverse.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: MultiverseApi
) : CharacterRepository {
    override suspend fun getCharacters(): List<Character> {
        return api.getCharacters().results.map { it.toDomain() }
    }
}
