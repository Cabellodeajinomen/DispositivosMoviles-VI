// GameRecord.kt
// Guarda una partida terminada dentro del historial de la sesion.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.model

data class GameRecord(
    val gameNumber: Int,
    val score: Int,
    val playedAt: String,
)
