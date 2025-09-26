package com.example.lab5.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5.data.PokemonRepository
import com.example.lab5.data.model.NamedApiResource
import kotlinx.coroutines.launch

data class ListUiState(
    val loading: Boolean = true,
    val items: List<NamedApiResource> = emptyList(),
    val error: String? = null
)

class PokemonListViewModel(
    private val repo: PokemonRepository = PokemonRepository()
) : ViewModel() {

    val uiState = mutableStateOf(ListUiState())

    init {
        viewModelScope.launch {
            runCatching { repo.listFirst100() }
                .onSuccess { uiState.value = ListUiState(loading = false, items = it.results) }
                .onFailure { uiState.value = ListUiState(loading = false, error = it.message) }
        }
    }
}

