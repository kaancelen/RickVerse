package com.bkaancelen.rickverse.presentation.screens.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.bkaancelen.rickverse.R
import com.bkaancelen.rickverse.domain.model.Character

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.space_xs)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.space_xxs)
        )
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.space_m))
        ) {
            // Image
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.iv_character_item))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.space_s))),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.space_m)))

            // Textual Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Status Dot
                    val color = when (character.status.lowercase()) {
                        "alive" -> Color.Green
                        "dead" -> Color.Red
                        else -> Color.Gray
                    }
                    Box(
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.space_xs))
                            .clip(CircleShape)
                            .background(color)
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.space_xxs)))
                    Text(text = character.status, style = MaterialTheme.typography.labelSmall)
                }

                Text(
                    text = stringResource(
                        R.string.character_item_line_2,
                        character.species,
                        character.gender
                    ),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = stringResource(R.string.character_item_line_3, character.originName),
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = stringResource(
                        R.string.character_item_line_4,
                        character.episodeCount,
                        character.firstSeenEpisodeNumber ?:0
                    ),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
