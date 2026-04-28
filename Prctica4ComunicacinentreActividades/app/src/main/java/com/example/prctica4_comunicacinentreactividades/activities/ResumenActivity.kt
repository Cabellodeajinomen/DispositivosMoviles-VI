/*
 * Descripcion: Muestra los datos del perfil y devuelve una confirmacion al formulario.
 * Autor: Alex Rhoddo P.
 * Fecha de creacion: 19-04-2026
 * Fecha de ultima modificacion: 19-04-2026
 */
package com.example.prctica4_comunicacinentreactividades.activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.prctica4_comunicacinentreactividades.R
import com.example.prctica4_comunicacinentreactividades.model.Usuario

class ResumenActivity : ComponentActivity() {

    private lateinit var tvNombreValor: TextView
    private lateinit var tvEdadValor: TextView
    private lateinit var tvCiudadValor: TextView
    private lateinit var tvCorreoValor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        enlazarVistas()

        val usuario = obtenerUsuarioDelIntent()
        if (usuario == null) {
            // Si no llegan datos, se cierra la pantalla para evitar un resumen inconsistente.
            Toast.makeText(this, getString(R.string.error_usuario_no_recibido), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        mostrarDatos(usuario)

        findViewById<Button>(R.id.btnConfirmar).setOnClickListener {
            devolverConfirmacion()
        }

        findViewById<Button>(R.id.btnVolverEditar).setOnClickListener {
            // Regresa sin confirmar para que el usuario pueda ajustar el formulario.
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun enlazarVistas() {
        tvNombreValor = findViewById(R.id.tvNombreValor)
        tvEdadValor = findViewById(R.id.tvEdadValor)
        tvCiudadValor = findViewById(R.id.tvCiudadValor)
        tvCorreoValor = findViewById(R.id.tvCorreoValor)
    }

    private fun mostrarDatos(usuario: Usuario) {
        tvNombreValor.text = usuario.nombre
        tvEdadValor.text = usuario.edad.toString()
        tvCiudadValor.text = usuario.ciudad
        tvCorreoValor.text = usuario.correo
    }

    // Compatibilidad con API recientes y anteriores para leer Serializable.
    private fun obtenerUsuarioDelIntent(): Usuario? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_USUARIO, Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_USUARIO) as? Usuario
        }
    }

    private fun devolverConfirmacion() {
        // Informa a FormularioActivity que el perfil fue confirmado por el usuario.
        val data = Intent().putExtra(EXTRA_CONFIRMADO, true)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    companion object {
        const val EXTRA_USUARIO = "extra_usuario"
        const val EXTRA_CONFIRMADO = "extra_confirmado"
    }
}





