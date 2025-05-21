package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bkaancelen.rickverse.R


@Composable
fun ResetFiltersButton(onReset: () -> Unit) {
    AssistChip(
        onClick = onReset,
        label = { Text(stringResource(R.string.character_reset_filter)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(R.string.character_reset_filter_cd)
            )
        }
    )
}
