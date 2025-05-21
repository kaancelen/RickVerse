package com.bkaancelen.rickverse.data.repository

import com.bkaancelen.rickverse.data.mapper.toDomain
import com.bkaancelen.rickverse.data.remote.api.MultiverseApi
import com.bkaancelen.rickverse.domain.model.CharactersList
import com.bkaancelen.rickverse.domain.repository.CharacterRepository
import kotlinx.coroutines.delay

class CharacterRepositoryImpl(
    private val api: MultiverseApi
) : CharacterRepository {

    override suspend fun getCharacters(
        name: String?,
        status: String?,
        gender: String?,
        page: Int
    ): CharactersList {
        delay(4000L)
        return api.getCharacters(name, status, gender, page).toDomain()
    }
}
