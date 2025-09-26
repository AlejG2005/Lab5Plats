package com.example.lab5.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    name: String,
    onBack: () -> Unit
) {
    val vm = remember { PokemonDetailViewModel(name) }
    val state by vm.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DetailFragment") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        when {
            state.loading -> Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            state.error != null -> Box(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                contentAlignment = Alignment.Center
            ) { Text("Error: ${state.error}") }

            else -> {
                val s = state.data!!.sprites
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Front")
                            AsyncImage(model = s.front_default, contentDescription = "front", modifier = Modifier.size(96.dp))
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Back")
                            AsyncImage(model = s.back_default, contentDescription = "back", modifier = Modifier.size(96.dp))
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Front Shiny")
                            AsyncImage(model = s.front_shiny, contentDescription = "front shiny", modifier = Modifier.size(96.dp))
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Back Shiny")
                            AsyncImage(model = s.back_shiny, contentDescription = "back shiny", modifier = Modifier.size(96.dp))
                        }
                    }
                }
            }
        }
    }
}
