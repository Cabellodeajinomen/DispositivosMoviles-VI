// AppNavigation.kt
// Grafo de navegacion del juego.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.colorgame.ui.screens.GameScreen
import com.example.colorgame.ui.screens.ResultScreen
import com.example.colorgame.ui.screens.WelcomeScreen
import com.example.colorgame.viewmodel.GameViewModel
import com.example.colorgame.viewmodel.GameViewModelFactory

@Composable
fun AppNavigation() {

    // Un solo ViewModel mantiene el estado compartido entre pantallas.
    val navController = rememberNavController()
    val context = LocalContext.current
    val gameViewModel: GameViewModel = viewModel(factory = GameViewModelFactory(context))

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        // Pantalla inicial con reglas y boton de inicio.
        composable("welcome") {
            WelcomeScreen(navController, gameViewModel)
        }

        // Pantalla principal donde viven el puntaje y el temporizador.
        composable("game") {
            GameScreen(navController, gameViewModel)
        }

        // Pantalla final con puntaje e historial.
        composable("result") {
            ResultScreen(navController, gameViewModel)
        }
    }
}