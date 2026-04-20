/*
 * Descripcion: Actividad puente de compatibilidad que redirige al formulario principal.
 * Autor: Alex Rhoddo P.
 * Fecha de creacion: 19-04-2026
 * Fecha de ultima modificacion: 19-04-2026
 */
package com.example.prctica4_comunicacinentreactividades

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.prctica4_comunicacinentreactividades.activities.FormularioActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mantiene compatibilidad si en algun momento se usa MainActivity como entrada.
        startActivity(Intent(this, FormularioActivity::class.java))
        finish()
    }
}