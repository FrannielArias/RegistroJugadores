package com.example.registrojugadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.registrojugadores.presentation.jugador.edit.EditJugadorScreen
import com.example.registrojugadores.presentation.jugador.edit.EditJugadorUiEvent
import com.example.registrojugadores.presentation.jugador.edit.EditJugadorViewModel
import com.example.registrojugadores.presentation.jugador.list.ListJugadorScreen
import com.example.registrojugadores.presentation.jugador.list.ListJugadorViewModel
import com.example.registrojugadores.presentation.navigation.TicTacToeNavHost
import dagger.hilt.android.AndroidEntryPoint
import com.example.registrojugadores.ui.theme.RegistroJugadoresTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroJugadoresTheme {
                val navHost = rememberNavController()
                TicTacToeNavHost(navHost)
            }
        }
    }
}

@Preview
@Composable
fun MainActivityPreview() {
    RegistroJugadoresTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                MainActivity()
            }
        }
    }
}
