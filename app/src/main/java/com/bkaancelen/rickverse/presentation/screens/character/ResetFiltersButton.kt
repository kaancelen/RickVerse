package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ResetFiltersButton(onReset: () -> Unit) {
    AssistChip(
        onClick = onReset,
        label = { Text("Reset Filters") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Reset"
            )
        }
    )
}
