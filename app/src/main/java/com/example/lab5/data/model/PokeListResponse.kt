package com.example.lab5.data.model

data class PokeListResponse(val results: List<NamedApiResource>)
data class NamedApiResource(val name: String, val url: String)


