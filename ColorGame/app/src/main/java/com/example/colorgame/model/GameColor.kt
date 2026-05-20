// GameColor.kt
// Opciones de color usadas por el juego.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame.model

import androidx.compose.ui.graphics.Color

// Cada opcion guarda el nombre, el color de fondo y el color del texto.
enum class GameColor(
    val label: String,
    val color: Color,
    val textColor: Color,
) {
    ROJO("Rojo", Color(0xFFE53935), Color.White),
    VERDE("Verde", Color(0xFF43A047), Color.White),
    AZUL("Azul", Color(0xFF1E88E5), Color.White),
    AMARILLO("Amarillo", Color(0xFFFDD835), Color.Black),
    MORADO("Morado", Color(0xFF8E24AA), Color.White),
}
