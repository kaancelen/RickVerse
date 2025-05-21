package com.bkaancelen.rickverse.data.mapper

import com.bkaancelen.rickverse.data.remote.dto.CharacterDto
import com.bkaancelen.rickverse.domain.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(id = id, name = name)
}
