// GameScreen.kt
// Main game screen with timer, score and color selection.
// Author: Alex Rhoddo Pacheco
package com.example.colorgame.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.colorgame.R
import com.example.colorgame.model.GameColor
import com.example.colorgame.viewmodel.GameViewModel

@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val animatedTargetColor by animateColorAsState(
        targetValue = uiState.targetColor.color,
        label = "targetColor",
    )

    // Start the timer and reset state when the screen appears.
    LaunchedEffect(Unit) {
        viewModel.startGame()
    }

    // Move to results when the timer ends.
    LaunchedEffect(uiState.isGameOver) {
        if (uiState.isGameOver) {
            navController.navigate("result") {
                popUpTo("welcome") { inclusive = false }
                launchSingleTop = true
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.game_title),
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(R.string.target_color_label),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    // Animated box shows the color to match.
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .padding(top = 12.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(animatedTargetColor),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "${stringResource(R.string.score_label)}: ${uiState.score}")
                Text(text = "${stringResource(R.string.time_label)}: ${uiState.timeLeft}s")
            }
            Spacer(modifier = Modifier.height(12.dp))
            // Feedback changes after each answer.
            Text(
                text = uiState.feedback,
                style = MaterialTheme.typography.titleMedium,
                color = when {
                    uiState.feedback.contains("Acierto") -> MaterialTheme.colorScheme.primary
                    uiState.feedback.contains("Error") -> MaterialTheme.colorScheme.error
                    uiState.feedback.contains("Tiempo") -> MaterialTheme.colorScheme.tertiary
                    else -> MaterialTheme.colorScheme.onSurface
                },
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Buttons are grouped by rows to keep the layout simple.
            uiState.options.chunked(3).forEach { rowOptions ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    rowOptions.forEach { option ->
                        ColorOptionButton(
                            option = option,
                            onClick = { viewModel.onColorSelected(option) },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun ColorOptionButton(
    option: GameColor,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(54.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = option.color,
            contentColor = option.textColor,
        ),
    ) {
        Text(text = option.label)
    }
}