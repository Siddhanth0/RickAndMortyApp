package com.example.characterapp.data.mappers

import com.example.characterapp.data.local.CharacterEntity
import com.example.characterapp.data.remote.CharacterDto
import com.example.characterapp.domain.Character

fun CharacterDto.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status =  status,
        species = species,
        image = image
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        imageUrl = image ?: ""
    )
}