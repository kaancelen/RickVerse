package com.bkaancelen.rickverse.data.mapper

import com.bkaancelen.rickverse.data.remote.dto.CharacterDto
import com.bkaancelen.rickverse.domain.model.Character

fun CharacterDto.toDomain(): Character {
    val firstEpisodeId = episode.firstOrNull()
        ?.substringAfterLast("/")
        ?.toIntOrNull()

    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        originName = origin.name,
        image = image,
        episodeCount = episode.size,
        firstSeenEpisodeNumber = firstEpisodeId
    )
}

