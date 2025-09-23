package com.example.registrojugadores.presentation.partida.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditPartidaScreen(
    viewModel: EditPartidaViewModel = hiltViewModel(),
    onCancel: () -> Unit = {},
    onSaveSuccess: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved) {
        if (state.saved) {
            onSaveSuccess()
        }
    }

    EditPartidaBody(
        state = state,
        onEvent = { event ->
            when (event) {
                EditPartidaUiEvent.Cancel -> onCancel()
                else -> viewModel.onEvent(event)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditPartidaBody(
    state: EditPartidaUiState,
    onEvent: (EditPartidaUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        if (state.jugadoresLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Fecha: ${state.fecha}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        var jugador1Expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = jugador1Expanded,
            onExpandedChange = { jugador1Expanded = it }
        ) {
            OutlinedTextField(
                value = state.listaJugadores.find { it.jugadorId == state.jugador1Id }?.nombres
                    ?: "Seleccione al Jugador 1",
                onValueChange = {},
                readOnly = true,
                label = { Text("Jugador 1") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = jugador1Expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .testTag("input_jugador1")
            )

            ExposedDropdownMenu(
                expanded = jugador1Expanded,
                onDismissRequest = { jugador1Expanded = false }
            ) {
                state.listaJugadores.forEach { jugador ->
                    DropdownMenuItem(
                        text = { Text(jugador.nombres) },
                        onClick = {
                            onEvent(EditPartidaUiEvent.Jugador1Changed(jugador.jugadorId))
                            jugador1Expanded = false
                        }
                    )
                }
            }
        }

        if (state.jugador1Error != null) {
            Text(
                text = state.jugador1Error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        var jugador2Expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = jugador2Expanded,
            onExpandedChange = { jugador2Expanded = it }
        ) {
            OutlinedTextField(
                value = state.listaJugadores.find { it.jugadorId == state.jugador2Id }?.nombres
                    ?: "Selecciona al Jugador 2",
                onValueChange = {},
                readOnly = true,
                label = { Text("Jugador 2") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = jugador2Expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .testTag("input_jugador2")
            )

            ExposedDropdownMenu(
                expanded = jugador2Expanded,
                onDismissRequest = { jugador2Expanded = false }
            ) {
                state.listaJugadores.forEach { jugador ->
                    DropdownMenuItem(
                        text = { Text(jugador.nombres) },
                        onClick = {
                            onEvent(EditPartidaUiEvent.Jugador2Changed(jugador.jugadorId))
                            jugador2Expanded = false
                        }
                    )
                }
            }
        }

        if (state.jugador2Error != null) {
            Text(
                text = state.jugador2Error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val ganadorSeleccionable = state.jugador1Id != null && state.jugador2Id != null

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Selecciona al ganador:",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Botón Jugador 1 - SIN colores personalizados
                Button(
                    onClick = { onEvent(EditPartidaUiEvent.GanadorChanged(state.jugador1Id!!)) },
                    enabled = ganadorSeleccionable,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = state.listaJugadores.find { it.jugadorId == state.jugador1Id }?.nombres
                            ?: "Jugador 1"
                    )
                }

                // Botón Jugador 2 - SIN colores personalizados
                Button(
                    onClick = { onEvent(EditPartidaUiEvent.GanadorChanged(state.jugador2Id!!)) },
                    enabled = ganadorSeleccionable,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = state.listaJugadores.find { it.jugadorId == state.jugador2Id }?.nombres
                            ?: "Jugador 2"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ganador: ${state.ganadorId?.let { id -> state.listaJugadores.find { it.jugadorId == id }?.nombres } ?: "Seleccione un ganador"}",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = state.esFinalizada,
                onCheckedChange = { checked ->
                    onEvent(EditPartidaUiEvent.EsFinalizadaChanged(checked))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "¿Terminó la partida?")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { onEvent(EditPartidaUiEvent.Save) },
                enabled = !state.jugadoresLoading && state.jugador1Id != 0 && state.jugador2Id != 0 && state.ganadorId != null,
                modifier = Modifier.testTag("btn_guardar")
            ) {
                Text("Guardar partida")
            }
            OutlinedButton(
                onClick = { onEvent(EditPartidaUiEvent.Cancel) },
                enabled = !state.jugadoresLoading
            ) {
                Text("Cancelar")
            }
        }
    }
}

@Preview
@Composable
fun EditPartidaBodyPreview() {
    val state = EditPartidaUiState()
    MaterialTheme {
        EditPartidaBody(state = state) { }
    }
}