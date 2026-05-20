// ResultScreen.kt
// Pantalla de resultados con puntaje final e historial de la sesion.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.colorgame.R
import com.example.colorgame.viewmodel.GameViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: GameViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Resumen final de la partida actual.
            Text(
                text = stringResource(R.string.result_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "${stringResource(R.string.final_score_label)}: ${uiState.score}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${stringResource(R.string.best_score_label)}: ${uiState.highScore}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = uiState.feedback)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // El historial de la sesion se mantiene solo como vista.
            Text(
                text = stringResource(R.string.history_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(uiState.history) { record ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Partida ${record.gameNumber}",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Puntaje: ${record.score}")
                            Text(text = "Fecha: ${record.playedAt}")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Reutiliza el mismo ViewModel: iniciar nueva partida y volver al juego.
            Button(onClick = {
                viewModel.startGame()
                navController.navigate("game") {
                    popUpTo("welcome") { inclusive = false }
                    launchSingleTop = true
                }
            }) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    }
}