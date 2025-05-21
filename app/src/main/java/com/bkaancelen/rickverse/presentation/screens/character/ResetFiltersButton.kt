package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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
        },
        modifier = Modifier.padding(8.dp)
    )
}
