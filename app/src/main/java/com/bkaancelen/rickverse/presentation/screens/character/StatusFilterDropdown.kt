package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.bkaancelen.rickverse.R

@Composable
fun StatusFilterDropdown(
    selected: String?,
    onSelected: (String?) -> Unit
) {
    val options = stringArrayResource(R.array.character_status_filter)
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(text = selected ?: stringResource(R.string.character_status_hint))
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status) },
                    onClick = {
                        onSelected(if (status == options.first()) null else status)
                        expanded = false
                    }
                )
            }
        }
    }
}
