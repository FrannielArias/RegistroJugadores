package com.example.registrojugadores.presentation.partidasApi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.registrojugadores.presentation.components.TopBarComponent
import com.example.registrojugadores.presentation.jugadorApi.JugadorScreen
import com.example.registrojugadores.presentation.tictactoe.PartidasViewModel

@Composable
fun PartidasApiScreen(
    onDrawer: () -> Unit = {},
    viewModel: PartidasViewModel = hiltViewModel()
) {
    val state by viewModel.ui.collectAsStateWithLifecycle()
    var showTablero by remember { mutableStateOf(false) }
    var partidaIdSeleccionada by remember { mutableStateOf<Int?>(null) }

    if (showTablero && partidaIdSeleccionada != null) {
        JugadorScreen(
            partidaIdArg = partidaIdSeleccionada
        )
    } else {
        LaunchedEffect(Unit) {
            viewModel.load()
        }

        Scaffold(
            topBar = {
                TopBarComponent(
                    title = "Partidas TicTacToe",
                    onDrawer
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.crear { partidaId ->
                            partidaIdSeleccionada = partidaId
                            showTablero = true
                        }
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Crear partida")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                PartidasListContent(
                    state = state,
                    onPartidaClick = { partidaId ->
                        partidaIdSeleccionada = partidaId
                        showTablero = true
                    }
                )
            }
        }
    }
}

@Composable
fun PartidasListContent(
    state: com.example.registrojugadores.presentation.tictactoe.PartidasUiState,
    onPartidaClick: (Int) -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    val cardBackgroundColor = if (isDarkTheme)
        Color(0xFF2D2F33) else Color.White

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (state.loading) {
            item {
                Text(
                    "Cargando...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (state.partidas.isEmpty() && !state.loading && state.error == null) {
            item {
                Text(
                    "No hay partidas. ¡Crea una nueva!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        items(state.partidas) { partida ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPartidaClick(partida.partidaId) },
                colors = CardDefaults.cardColors(
                    containerColor = cardBackgroundColor
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                ListItem(
                    headlineContent = {
                        Text("Partida ${partida.partidaId}")
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent
                    )
                )
            }
        }

        if (state.error != null) {
            item {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}