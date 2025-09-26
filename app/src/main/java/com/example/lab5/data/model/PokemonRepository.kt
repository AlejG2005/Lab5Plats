package com.example.lab5.data

import com.example.lab5.data.model.PokeListResponse
import com.example.lab5.data.model.PokemonDetail
import com.example.lab5.data.remote.RetrofitModule

class PokemonRepository {
    private val api = RetrofitModule.api

    suspend fun listFirst100(): PokeListResponse = api.getPokemonList(limit = 100)
    suspend fun getDetail(name: String): PokemonDetail = api.getPokemonDetail(name)
}

