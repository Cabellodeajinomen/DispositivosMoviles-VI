package com.example.practica5formulariomejorado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica5formulariomejorado.model.FormData
import com.example.practica5formulariomejorado.model.RegistrationResult
import com.example.practica5formulariomejorado.ui.components.ActiveUserSwitch
import com.example.practica5formulariomejorado.ui.components.CharacterCounter
import com.example.practica5formulariomejorado.ui.components.FormTextField
import com.example.practica5formulariomejorado.ui.components.GenderSelector
import com.example.practica5formulariomejorado.ui.components.LevelSlider
import com.example.practica5formulariomejorado.ui.components.TermsCheckbox
import com.example.practica5formulariomejorado.util.FormValidator

/**
 * Pantalla principal del formulario mejorado
 * Implementa todos los requisitos de la práctica 5:
 * - Campos obligatorios: nombre, edad, correo
 * - Componentes: RadioButton, Checkbox, Switch, Slider
 * - Validaciones completas
 * - Botón inteligente
 * - Mostrar resultado
 * - Botón limpiar
 * - Extras: mensajes de error, color del resultado, contador de caracteres
 */
@Composable
fun ImprovedFormScreen() {
    // Estado del formulario
    var formData by remember { mutableStateOf(FormData()) }

    // Errores de validación
    var formErrors by remember { mutableStateOf(FormValidator.validateForm(formData)) }

    // Estado del resultado
    var registrationResult by remember { mutableStateOf<RegistrationResult?>(null) }

    // Función para actualizar el formulario y revalidar
    fun updateFormData(newData: FormData) {
        formData = newData
        formErrors = FormValidator.validateForm(newData)
    }

    // Función para registrar/enviar el formulario
    fun submitForm() {
        if (FormValidator.isFormValid(formErrors)) {
            registrationResult = RegistrationResult(
                userName = formData.name,
                userAge = formData.age,
                userEmail = formData.email,
                userGender = formData.gender,
                isUserActive = formData.isActive,
                userLevel = formData.level.toInt(),
                isSuccess = true
            )
        }
    }

    // Función para limpiar el formulario
    fun clearForm() {
        formData = FormData()
        formErrors = FormValidator.validateForm(formData)
        registrationResult = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .background(Color.White)
    ) {
        // Título
        Text(
            text = "Formulario de Registro",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Sección de campos obligatorios
        Text(
            text = "Información Personal",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo Nombre con contador de caracteres
        FormTextField(
            value = formData.name,
            onValueChange = {
                if (it.length <= 50) updateFormData(formData.copy(name = it))
            },
            label = "Nombre",
            error = formErrors.nameError,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        CharacterCounter(formData.name.length)
        Spacer(modifier = Modifier.height(16.dp))

        // Campo Edad
        FormTextField(
            value = formData.age,
            onValueChange = {
                if (it.isEmpty() || it.all { char -> char.isDigit() }) {
                    updateFormData(formData.copy(age = it))
                }
            },
            label = "Edad",
            error = formErrors.ageError,
            keyboardType = KeyboardType.Number,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo Correo
        FormTextField(
            value = formData.email,
            onValueChange = { updateFormData(formData.copy(email = it)) },
            label = "Correo Electrónico",
            error = formErrors.emailError,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sección de componentes adicionales
        Text(
            text = "Información Adicional",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Selector de Género (RadioButton)
        GenderSelector(
            selectedGender = formData.gender,
            onGenderSelected = { updateFormData(formData.copy(gender = it)) },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Switch Usuario Activo
        ActiveUserSwitch(
            isActive = formData.isActive,
            onActiveChange = { updateFormData(formData.copy(isActive = it)) }
        )

        // Slider Nivel de Experiencia
        LevelSlider(
            level = formData.level,
            onLevelChange = { updateFormData(formData.copy(level = it)) },
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Checkbox Términos
        TermsCheckbox(
            acceptTerms = formData.acceptTerms,
            onTermsChange = { updateFormData(formData.copy(acceptTerms = it)) },
            error = formErrors.termsError,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Botones de acción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            // Botón Registrar (inteligente - deshabilitado si hay errores)
            Button(
                onClick = { submitForm() },
                enabled = FormValidator.isFormValid(formErrors),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Registrar")
            }

            // Botón Limpiar
            Button(
                onClick = { clearForm() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpiar")
            }
        }

        // Mostrar resultado si existe
        if (registrationResult != null && registrationResult!!.isSuccess) {
            Spacer(modifier = Modifier.height(24.dp))

            // Card con resultado (con fondo verde cuando es válido)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)  // Verde claro
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "✓ Registro Exitoso",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32),  // Verde oscuro
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = buildString {
                            append("Usuario: ${registrationResult!!.userName}\n")
                            append("Edad: ${registrationResult!!.userAge} años\n")
                            append("Correo: ${registrationResult!!.userEmail}\n")
                            append("Género: ${registrationResult!!.userGender}\n")
                            append("Estado: ${if (registrationResult!!.isUserActive) "Activo" else "Inactivo"}\n")
                            append("Nivel de Experiencia: ${registrationResult!!.userLevel}/10")
                        },
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

