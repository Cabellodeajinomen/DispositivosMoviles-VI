// GameUiState.kt
// Estado de UI expuesto por el GameViewModel.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.model

// La UI lee este estado con collectAsState.
data class GameUiState(
    val score: Int = 0,
    val highScore: Int = 0,
    val timeLeft: Int = 30,
    val feedback: String = "",
    val isGameRunning: Boolean = false,
    val isGameOver: Boolean = false,
    val gameNumber: Int = 1,
    val targetColor: GameColor = GameColor.ROJO,
    val options: List<GameColor> = GameColor.entries.toList(),
    val history: List<GameRecord> = emptyList(),
)
