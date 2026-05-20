// GameViewModel.kt
// Contiene el estado y la logica principal del juego.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.colorgame.model.GameColor
import com.example.colorgame.model.GameRecord
import com.example.colorgame.model.GameUiState
import com.example.colorgame.utils.ScorePreferences
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val scorePreferences: ScorePreferences,
) : ViewModel() {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    private var timerJob: Job? = null

    // Fuente unica de verdad para la UI.
    private val _uiState = MutableStateFlow(
        GameUiState(highScore = scorePreferences.getHighScore()),
    )
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun startGame() {
        timerJob?.cancel()
        // Reinicia el puntaje y el color objetivo para una nueva partida.
        _uiState.update {
            it.copy(
                score = 0,
                timeLeft = START_TIME,
                feedback = "Empieza el juego",
                isGameRunning = true,
                isGameOver = false,
                highScore = scorePreferences.getHighScore(),
                targetColor = randomColor(),
            )
        }
        startTimer()
    }

    fun onColorSelected(selectedColor: GameColor) {
        val currentState = _uiState.value
        if (!currentState.isGameRunning || currentState.isGameOver || currentState.timeLeft <= 0) {
            return
        }

        if (selectedColor == currentState.targetColor) {
            // Acierto: suma un punto y cambia el color a adivinar.
            val newScore = currentState.score + 1
            val newTarget = randomColor(exclude = currentState.targetColor)
            _uiState.update {
                it.copy(
                    score = newScore,
                    feedback = "¡Acierto!",
                    targetColor = newTarget,
                    highScore = maxOf(it.highScore, newScore),
                )
            }
        } else {
            // Error: solo actualiza el mensaje sin detener la partida.
            _uiState.update {
                it.copy(
                    feedback = "Error, intenta de nuevo",
                )
            }
        }
    }

    private fun startTimer() {
        // Corrutina que descuenta un segundo por vez.
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                val currentState = _uiState.value
                if (!currentState.isGameRunning) {
                    break
                }
                if (currentState.timeLeft <= 1) {
                    finishGame()
                    break
                }
                _uiState.update { state ->
                    state.copy(timeLeft = state.timeLeft - 1)
                }
            }
        }
    }

    private fun finishGame() {
        val currentState = _uiState.value
        if (currentState.isGameOver) {
            return
        }

        timerJob?.cancel()
        val updatedHighScore = maxOf(currentState.highScore, currentState.score)
        // Guarda solo el mejor puntaje historico.
        scorePreferences.saveHighScore(updatedHighScore)
        val nextHistory = currentState.history + GameRecord(
            gameNumber = currentState.gameNumber,
            score = currentState.score,
            playedAt = LocalDateTime.now().format(dateFormatter),
        )
        _uiState.update {
            it.copy(
                highScore = updatedHighScore,
                isGameRunning = false,
                isGameOver = true,
                feedback = "Tiempo agotado",
                history = nextHistory,
                gameNumber = it.gameNumber + 1,
            )
        }
    }

    private fun randomColor(exclude: GameColor? = null): GameColor {
        val options = GameColor.entries.filterNot { it == exclude }
        return options[Random.nextInt(options.size)]
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }

    private companion object {
        const val START_TIME = 30
    }
}

class GameViewModelFactory(
    private val context: Context,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(ScorePreferences(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
