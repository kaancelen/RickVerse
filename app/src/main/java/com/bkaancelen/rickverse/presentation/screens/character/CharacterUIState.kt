package com.bkaancelen.rickverse.presentation.screens.character

import com.bkaancelen.rickverse.domain.model.Character

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val errorMessage: String? = null
)
