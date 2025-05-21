package com.bkaancelen.rickverse.domain.useCase

import com.bkaancelen.rickverse.domain.model.Character
import com.bkaancelen.rickverse.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): List<Character> {
        return repository.getCharacters()
    }
}
