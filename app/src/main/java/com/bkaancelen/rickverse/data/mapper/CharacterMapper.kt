package com.bkaancelen.rickverse.data.mapper

import com.bkaancelen.rickverse.data.remote.dto.CharacterDto
import com.bkaancelen.rickverse.data.remote.dto.CharactersListDto
import com.bkaancelen.rickverse.data.remote.dto.PageInfoDto
import com.bkaancelen.rickverse.domain.model.Character
import com.bkaancelen.rickverse.domain.model.CharactersList
import com.bkaancelen.rickverse.domain.model.PageInfo

fun CharactersListDto.toDomain(): CharactersList {
    return CharactersList(
        pageInfo = info.toDomain(),
        characters = results.map { it.toDomain() }
    )
}

fun PageInfoDto.toDomain(): PageInfo {
    return PageInfo(
        totalPages = pages
    )
}

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

