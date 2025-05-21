package com.bkaancelen.rickverse.domain.useCase

import com.bkaancelen.rickverse.domain.model.CharactersList
import com.bkaancelen.rickverse.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(
        name: String? = null,
        status: String? = null,
        gender: String? = null,
        page: Int = 1
    ): CharactersList {
        return repository.getCharacters(name, status, gender, page)
    }
}
