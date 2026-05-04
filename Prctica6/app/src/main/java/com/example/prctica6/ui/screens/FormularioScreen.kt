/**
 * Descripcion corta: Presenta un formulario simple para capturar nombre y telefono, y regresar a la lista al guardar.
 * Autor: Alex Rhoddo P
 * Fecha de creacion: 2026-05-03
 * Fecha de ultima modificacion: 2026-05-03
 */
package com.example.prctica6.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Pantalla del formulario: conserva el texto con remember y solo regresa al presionar guardar.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    onGuardar: () -> Unit
) {
    // Usamos rememberSaveable para que el texto no se borre al rotar la pantalla.
    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }

    // Se definen reglas simples para mostrar aviso cuando el usuario escriba caracteres no permitidos.
    val nombreEsValido = nombre.isBlank() || nombre.trim().matches(Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
    val telefonoEsValido = telefono.isBlank() || telefono.matches(Regex("^[0-9]+$"))
    val camposCompletos = nombre.isNotBlank() && telefono.isNotBlank()
    val formularioValido = camposCompletos && nombreEsValido && telefonoEsValido

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Formulario de contacto") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Llena los campos para practicar la navegación entre pantallas.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Campo de texto para el nombre del contacto.
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nombre") },
                singleLine = true,
                isError = nombre.isNotBlank() && !nombreEsValido,
                supportingText = {
                    Text(text = "Solo letras y espacios.")
                }
            )

            // Campo de texto para el telefono o la edad, segun se desee adaptar el ejercicio.
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Teléfono") },
                singleLine = true,
                isError = telefono.isNotBlank() && !telefonoEsValido,
                supportingText = {
                    Text(text = "Solo números sin símbolos.")
                }
            )

            if (!formularioValido && camposCompletos) {
                Text(
                    text = "Corrige los campos marcados en rojo antes de guardar.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = onGuardar,
                enabled = formularioValido,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Guardar")
            }
        }
    }
}


