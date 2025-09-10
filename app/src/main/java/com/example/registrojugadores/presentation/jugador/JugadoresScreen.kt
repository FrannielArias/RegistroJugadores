package com.example.registrojugadores.presentation.jugador

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registrojugadores.presentation.components.TopBarComponent
import com.example.registrojugadores.presentation.jugador.edit.EditJugadorScreen
import com.example.registrojugadores.presentation.jugador.edit.EditJugadorViewModel
import com.example.registrojugadores.presentation.jugador.list.ListJugadorScreen
import com.example.registrojugadores.presentation.jugador.list.ListJugadorViewModel
import com.example.registrojugadores.ui.theme.RegistroJugadoresTheme

@Composable
fun JugadoresScreen(
    onDrawer: () -> Unit = {},
    editViewModel: EditJugadorViewModel = hiltViewModel(),
    listViewModel: ListJugadorViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Registro de Jugadores",
                onDrawer
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            JugadoresScreenBody(editViewModel, listViewModel)
        }
    }
}

@Composable
fun JugadoresScreenBody(
    edit: EditJugadorViewModel,
    list: ListJugadorViewModel
) {
    EditJugadorScreen(edit)
    ListJugadorScreen(list)
}

@Preview
@Composable
fun JugadoresScreenPreview() {
    RegistroJugadoresTheme {
        JugadoresScreenBody(hiltViewModel(), hiltViewModel())
    }
}