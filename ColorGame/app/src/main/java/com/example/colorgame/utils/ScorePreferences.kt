// ScorePreferences.kt
// Guarda el mejor puntaje con SharedPreferences.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.utils

import android.content.Context
import androidx.core.content.edit

// Esta clase se encarga de guardar y recuperar el puntaje más alto usando SharedPreferences.
class ScorePreferences(context: Context) {

    private val preferences = context.applicationContext.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE,
    )

    fun getHighScore(): Int = preferences.getInt(KEY_HIGH_SCORE, 0)

    fun saveHighScore(score: Int) {
        if (score > getHighScore()) {
            // Guarda solo si el nuevo puntaje es mejor.
            preferences.edit { putInt(KEY_HIGH_SCORE, score) }
        }
    }

    private companion object {
        const val PREFS_NAME = "color_game_prefs"
        const val KEY_HIGH_SCORE = "high_score"
    }
}
