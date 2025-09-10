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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import com.example.registrojugadores.presentation.edit.EditJugadorScreen
import com.example.registrojugadores.presentation.edit.EditJugadorViewModel
import com.example.registrojugadores.presentation.edit.EditJugadorUiEvent
import com.example.registrojugadores.presentation.list.ListJugadorScreen
import com.example.registrojugadores.presentation.list.ListJugadorViewModel
import com.example.registrojugadores.presentation.list.ListJugadorUiEvent
import com.example.registrojugadores.ui.theme.RegistroJugadoresTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroJugadoresTheme {
                val editViewModel: EditJugadorViewModel = hiltViewModel()
                val listViewModel: ListJugadorViewModel = hiltViewModel()
                val listState by listViewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(listState.navigateToEditId) {
                    listState.navigateToEditId?.let { id ->
                        editViewModel.onEvent(EditJugadorUiEvent.Load(id))
                        listViewModel.onNavigationHandled()
                    }
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Registro de jugadores",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        EditJugadorScreen(viewModel = editViewModel)
                        ListJugadorScreen(viewModel = listViewModel)
                    }
                }
            }
        }
    }
}