package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bkaancelen.rickverse.domain.useCase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    var nameQuery by mutableStateOf<String?>(null)
        private set
    var statusQuery by mutableStateOf<String?>(null)
        private set
    var genderQuery by mutableStateOf<String?>(null)
        private set
    var currentPage = 1
        private set

    private var totalPages = 1
    private var isLoadingMore = false
    private var searchJob: Job? = null

    var uiState by mutableStateOf(CharacterUiState())
        private set

    init {
        loadCharacters(reset = true)
    }

    fun onNameQueryChanged(query: String) {
        nameQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            loadCharacters()
        }
    }

    fun onStatusChanged(status: String?) {
        statusQuery = status
        loadCharacters()
    }

    fun onGenderChanged(gender: String?) {
        genderQuery = gender
        loadCharacters()
    }

    fun resetFilters() {
        nameQuery = null
        statusQuery = null
        genderQuery = null
        loadCharacters()
    }

    fun onScrollLoad() {
        loadCharacters(reset = false)
    }


    private fun loadCharacters(reset: Boolean = true) {
        if (isLoadingMore || (currentPage > totalPages && !reset)) return

        viewModelScope.launch {
            if (reset) {
                currentPage = 1
                totalPages = 1
                uiState = CharacterUiState(isLoading = true)
            } else {
                isLoadingMore = true
                uiState = uiState.copy(isLoading = true)
            }

            try {
                val response = getCharactersUseCase(
                    name = nameQuery,
                    status = statusQuery,
                    gender = genderQuery,
                    page = currentPage
                )
                totalPages = response.pageInfo.totalPages

                val newList = if (reset) {
                    response.characters
                } else {
                    uiState.characters + response.characters
                }

                uiState = uiState.copy(
                    characters = newList,
                    isLoading = false,
                    errorMessage = null
                )

                currentPage++
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            } finally {
                isLoadingMore = false
            }
        }
    }
}
