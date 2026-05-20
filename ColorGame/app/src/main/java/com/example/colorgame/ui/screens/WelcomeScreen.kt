// WelcomeScreen.kt
// Pantalla de bienvenida con reglas y boton de inicio.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.colorgame.viewmodel.GameViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.colorgame.R

@Composable
fun WelcomeScreen(navController: NavController, viewModel: GameViewModel) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Titulo que ve el usuario antes de iniciar.
            Text(
                text = stringResource(R.string.welcome_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta con las reglas del juego.
            Card(colors = CardDefaults.cardColors()) {
                Text(
                    text = stringResource(R.string.welcome_rules),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(20.dp),
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Boton que inicia la partida y navega a la pantalla del juego.
            Button(onClick = {
                // Iniciamos la partida en el ViewModel para que el estado
                // se conserve si la actividad se recrea (rotacion, etc.).
                viewModel.startGame()
                navController.navigate("game") {
                    launchSingleTop = true
                }
            }) {
                Text(text = stringResource(R.string.start_game))
            }
        }
    }
}