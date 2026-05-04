/**
 * Descripcion corta: Punto de entrada de la aplicacion que carga el tema visual y la navegacion principal.
 * Autor: Alex Rhoddo P
 * Fecha de creacion: 2026-05-03
 * Fecha de ultima modificacion: 2026-05-03
 */
package com.example.prctica6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.prctica6.ui.AppNavGraph
import com.example.prctica6.ui.theme.Practica6Theme

// Activity principal: solo inicializa el contenido Compose de la aplicacion.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica6Theme {
                AppNavGraph()
            }
        }
    }
}
