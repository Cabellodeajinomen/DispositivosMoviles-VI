/*
 * Descripcion: Modelo serializable para transportar datos del perfil entre actividades.
 * Autor: Alex Rhoddo P.
 * Fecha de creacion: 26-04-2026
 * Fecha de ultima modificacion: 27-04-2026
 */

package com.example.practica5formulariomejorado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.example.practica5formulariomejorado.ui.screens.ImprovedFormScreen

// MainActivity - Punto de entrada de la aplicacion
// Implementa la Practica 5: Formulario Mejorado con Componentes de Compose
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar la interfaz usando Jetpack Compose
        setContent {
            // Tema Material3 con superficie
            MaterialTheme {
                Surface(
                    color = Color.White
                ) {
                    // Mostrar la pantalla del formulario mejorado
                    ImprovedFormScreen()
                }
            }
        }
    }
}