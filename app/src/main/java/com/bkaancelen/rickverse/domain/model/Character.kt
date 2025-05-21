package com.bkaancelen.rickverse.domain.model

data class CharactersList(
    val pageInfo: PageInfo,
    val characters: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val originName: String,
    val image: String,
    val episodeCount: Int,
    val firstSeenEpisodeNumber: Int?
)

data class PageInfo(
    val totalPages: Int
)
