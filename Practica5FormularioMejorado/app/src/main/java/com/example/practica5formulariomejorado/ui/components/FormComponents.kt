package com.example.practica5formulariomejorado.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Campo de texto personalizado con validación
 * Muestra un mensaje de error debajo del campo si existe
 */
@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth(),
            isError = error != null
        )
        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

/**
 * Grupo de radio buttons para seleccionar género
 * Componente obligatorio según la práctica
 */
@Composable
fun GenderSelector(
    selectedGender: String,
    onGenderSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("Género", fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        val options = listOf("Masculino", "Femenino", "Otro")
        options.forEach { gender ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedGender == gender,
                    onClick = { onGenderSelected(gender) }
                )
                Text(gender, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

/**
 * Switch para indicar si el usuario está activo
 * Componente obligatorio según la práctica
 */
@Composable
fun ActiveUserSwitch(
    isActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Usuario Activo", modifier = Modifier.padding(start = 8.dp))
        Switch(
            checked = isActive,
            onCheckedChange = onActiveChange,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

/**
 * Slider para seleccionar el nivel de experiencia (0-10)
 * Componente obligatorio según la práctica
 */
@Composable
fun LevelSlider(
    level: Float,
    onLevelChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Nivel de Experiencia: ${level.toInt()}")
        }
        Slider(
            value = level,
            onValueChange = onLevelChange,
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Checkbox para aceptar términos
 * Componente obligatorio según la práctica
 * Muestra mensaje de error si no está seleccionado
 */
@Composable
fun TermsCheckbox(
    acceptTerms: Boolean,
    onTermsChange: (Boolean) -> Unit,
    error: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = onTermsChange
            )
            Text("Acepto términos y condiciones", modifier = Modifier.padding(start = 8.dp))
        }
        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

/**
 * Contador de caracteres para el nombre
 * Componente extra para mejorar la UX
 */
@Composable
fun CharacterCounter(
    currentLength: Int,
    maxLength: Int = 50,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$currentLength/$maxLength caracteres",
        fontSize = 12.sp,
        color = if (currentLength > maxLength) Color.Red else Color.Gray,
        modifier = modifier.padding(top = 4.dp)
    )
}

