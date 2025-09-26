package com.example.lab5.util

fun idFromUrl(url: String): String =
    url.trimEnd('/').substringAfterLast('/')

fun listThumbUrl(id: String): String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"


