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

/*
 * Descripcion: Muestra los datos del perfil y devuelve una confirmacion al formulario.
 * Autor: Usuario
 * Fecha de creacion: 2026-04-19
 * Fecha de ultima modificacion: 2026-04-19
 */
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
            Toast.makeText(this, getString(R.string.error_usuario_no_recibido), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        mostrarDatos(usuario)

        findViewById<Button>(R.id.btnConfirmar).setOnClickListener {
            devolverConfirmacion()
        }

        findViewById<Button>(R.id.btnVolverEditar).setOnClickListener {
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
        val data = Intent().putExtra(EXTRA_CONFIRMADO, true)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    companion object {
        const val EXTRA_USUARIO = "extra_usuario"
        const val EXTRA_CONFIRMADO = "extra_confirmado"
    }
}

