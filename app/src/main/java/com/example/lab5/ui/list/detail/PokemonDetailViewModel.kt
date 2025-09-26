package com.example.lab5.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5.data.PokemonRepository
import com.example.lab5.data.model.PokemonDetail
import kotlinx.coroutines.launch

data class DetailUiState(
    val loading: Boolean = true,
    val data: PokemonDetail? = null,
    val error: String? = null
)

class PokemonDetailViewModel(
    private val name: String,
    private val repo: PokemonRepository = PokemonRepository()
) : ViewModel() {

    val uiState = mutableStateOf(DetailUiState())

    init {
        viewModelScope.launch {
            runCatching { repo.getDetail(name) }
                .onSuccess { uiState.value = DetailUiState(loading = false, data = it) }
                .onFailure { uiState.value = DetailUiState(loading = false, error = it.message) }
        }
    }
}

