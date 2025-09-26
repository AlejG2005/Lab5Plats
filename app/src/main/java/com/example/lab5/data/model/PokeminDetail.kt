package com.example.lab5.data.model

data class PokemonDetail(val name: String, val sprites: Sprites)

data class Sprites(
    val front_default: String?,
    val back_default: String?,
    val front_shiny: String?,
    val back_shiny: String?
)


