package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    val state = viewModel.uiState

    Column(modifier = Modifier.fillMaxSize()) {
        // Search
        OutlinedTextField(
            value = viewModel.nameQuery.orEmpty(),
            onValueChange = viewModel::onNameQueryChanged,
            label = { Text("Search by name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            maxLines = 1
        )

        // Filters
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            StatusFilterDropdown(
                selected = viewModel.statusQuery,
                onSelected = viewModel::onStatusChanged,
            )
            GenderFilterDropdown(
                selected = viewModel.genderQuery,
                onSelected = viewModel::onGenderChanged
            )
            ResetFiltersButton(
                onReset = viewModel::resetFilters
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Content
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("❌ ${state.errorMessage}")
                }
            }

            state.characters.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No characters found.")
                }
            }

            else -> {
                LazyColumn {
                    items(state.characters) { character ->
                        CharacterItem(character)
                    }
                }
            }
        }
    }
}
