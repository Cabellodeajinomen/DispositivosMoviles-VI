// MainActivity.kt
// Punto de entrada de la app.
// Autor: Alex Rhoddo Pacheco
package com.example.colorgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.colorgame.navigation.AppNavigation
import com.example.colorgame.ui.theme.ColorGameTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Envuelve toda la app en el tema compartido.
        setContent {
            ColorGameTheme {
                AppNavigation()
            }
        }
    }
}