package com.example.lab5.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.lab5.util.idFromUrl
import com.example.lab5.util.listThumbUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    vm: PokemonListViewModel = viewModel(),
    onPokemonClick: (String) -> Unit
) {
    val state by vm.uiState

    Scaffold(
        topBar = { TopAppBar(title = { Text("MainFragment") }) }
    ) { padding ->
        when {
            state.loading -> Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            state.error != null -> Box(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${state.error}")
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.items) { item ->
                        val id = idFromUrl(item.url)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onPokemonClick(item.name) }
                        ) {
                            Row(Modifier.padding(12.dp)) {
                                AsyncImage(
                                    model = listThumbUrl(id),
                                    contentDescription = item.name,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    item.name.replaceFirstChar { it.uppercase() },
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
