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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bkaancelen.rickverse.R

@Composable
fun CharacterScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    val state = viewModel.uiState

    Column(modifier = Modifier.fillMaxSize()) {
        // Search
        OutlinedTextField(
            value = viewModel.nameQuery.orEmpty(),
            onValueChange = viewModel::onNameQueryChanged,
            label = { Text(stringResource(R.string.character_otf_search_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.space_xs)),
            maxLines = 1
        )

        // Filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.space_xs))
        ) {
            StatusFilterDropdown(
                selected = viewModel.statusQuery,
                onSelected = viewModel::onStatusChanged,
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.space_xs)))
            GenderFilterDropdown(
                selected = viewModel.genderQuery,
                onSelected = viewModel::onGenderChanged
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.space_xs)))
            ResetFiltersButton(
                onReset = viewModel::resetFilters
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_xs)))

        // Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = dimensionResource(R.dimen.space_xs),
                end = dimensionResource(R.dimen.space_xs),
                bottom = dimensionResource(R.dimen.space_l)
            )
        ) {
            when {
                state.errorMessage != null -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(stringResource(R.string.character_error_message, state.errorMessage))
                        }
                    }
                }

                state.characters.isEmpty() -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(stringResource(R.string.character_not_found))
                        }
                    }
                }

                else -> {
                    itemsIndexed(state.characters, key = { _, item -> item.id }) { index, item ->
                        CharacterItem(item)

                        if (index >= state.characters.lastIndex.minus(5)) run {
                            viewModel.onScrollLoad()
                        }
                    }
                }
            }

            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.space_l)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
