package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
            Spacer(modifier = Modifier.padding(8.dp))
            GenderFilterDropdown(
                selected = viewModel.genderQuery,
                onSelected = viewModel::onGenderChanged
            )
            Spacer(modifier = Modifier.padding(8.dp))
            ResetFiltersButton(
                onReset = viewModel::resetFilters
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Content
        when {
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 24.dp)
                ) {
                    itemsIndexed(state.characters, key = { _, item -> item.id }) { index, item ->
                        CharacterItem(item)

                        if (index == state.characters.lastIndex.minus(5)) run {
                            viewModel.onScrollLoad()
                        }
                    }

                    // Loading
                    item {
                        if (state.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}
