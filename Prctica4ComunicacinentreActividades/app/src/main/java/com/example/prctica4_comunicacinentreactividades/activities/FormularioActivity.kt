/*
 * Descripcion: Captura los datos del perfil y abre una pantalla de resumen para confirmar.
 * Autor: Alex Rhoddo P.
 * Fecha de creacion: 19-04-2026
 * Fecha de ultima modificacion: 19-04-2026
 */
package com.example.prctica4_comunicacinentreactividades.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.prctica4_comunicacinentreactividades.R
import com.example.prctica4_comunicacinentreactividades.model.Usuario

class FormularioActivity : ComponentActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etCiudad: EditText
    private lateinit var etCorreo: EditText

    // Recibe la respuesta de ResumenActivity para saber si el usuario confirmo el perfil.
    private val resumenLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { resultado ->
        val confirmado = resultado.resultCode == Activity.RESULT_OK &&
            resultado.data?.getBooleanExtra(ResumenActivity.EXTRA_CONFIRMADO, false) == true

        if (confirmado) {
            Toast.makeText(this, getString(R.string.toast_perfil_guardado), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        enlazarVistas()
        restaurarCampos(savedInstanceState)

        findViewById<Button>(R.id.btnContinuar).setOnClickListener {
            enviarDatosAResumen()
        }

        findViewById<Button>(R.id.btnLimpiar).setOnClickListener {
            limpiarCampos()
        }
    }

    private fun enlazarVistas() {
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etCiudad = findViewById(R.id.etCiudad)
        etCorreo = findViewById(R.id.etCorreo)
    }

    private fun restaurarCampos(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return

        // Restaura lo escrito cuando la actividad se recrea (por ejemplo, al rotar pantalla).
        etNombre.setText(savedInstanceState.getString(STATE_NOMBRE, ""))
        etEdad.setText(savedInstanceState.getString(STATE_EDAD, ""))
        etCiudad.setText(savedInstanceState.getString(STATE_CIUDAD, ""))
        etCorreo.setText(savedInstanceState.getString(STATE_CORREO, ""))
    }

    // Valida entradas del formulario antes de navegar a la pantalla de resumen.
    private fun enviarDatosAResumen() {
        val nombre = etNombre.text.toString().trim()
        val edadTexto = etEdad.text.toString().trim()
        val ciudad = etCiudad.text.toString().trim()
        val correo = etCorreo.text.toString().trim()

        // Primera validacion: todos los campos son obligatorios.
        if (nombre.isEmpty() || edadTexto.isEmpty() || ciudad.isEmpty() || correo.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_campos_obligatorios), Toast.LENGTH_SHORT).show()
            return
        }

        // Segunda validacion: control de rango para evitar edades no realistas.
        val edad = edadTexto.toIntOrNull()
        if (edad == null || edad !in 1..120) {
            Toast.makeText(this, getString(R.string.error_edad_invalida), Toast.LENGTH_SHORT).show()
            return
        }

        // Tercera validacion: formato basico del correo.
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, getString(R.string.error_correo_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        // Si todo es valido, se envia el objeto Usuario para mostrar el resumen.
        val usuario = Usuario(nombre = nombre, edad = edad, ciudad = ciudad, correo = correo)
        val intent = Intent(this, ResumenActivity::class.java).putExtra(ResumenActivity.EXTRA_USUARIO, usuario)
        resumenLauncher.launch(intent)
    }

    private fun limpiarCampos() {
        etNombre.text.clear()
        etEdad.text.clear()
        etCiudad.text.clear()
        etCorreo.text.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Guarda el contenido actual para no perder lo escrito al recrear la actividad.
        outState.putString(STATE_NOMBRE, etNombre.text.toString())
        outState.putString(STATE_EDAD, etEdad.text.toString())
        outState.putString(STATE_CIUDAD, etCiudad.text.toString())
        outState.putString(STATE_CORREO, etCorreo.text.toString())
    }

    companion object {
        private const val STATE_NOMBRE = "state_nombre"
        private const val STATE_EDAD = "state_edad"
        private const val STATE_CIUDAD = "state_ciudad"
        private const val STATE_CORREO = "state_correo"
    }
}





