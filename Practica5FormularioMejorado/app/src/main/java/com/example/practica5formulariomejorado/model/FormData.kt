package com.example.practica5formulariomejorado.model

/**
 * Clase que representa los datos del formulario
 * Contiene todos los campos del formulario incluyendo validaciones
 */
data class FormData(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val gender: String = "Otro",  // RadioButton: Masculino, Femenino, Otro
    val isActive: Boolean = false,  // Switch: usuario activo/inactivo
    val level: Float = 0f,  // Slider: nivel de experiencia (0-10)
    val acceptTerms: Boolean = false  // Checkbox: aceptar términos
)

/**
 * Clase para gestionar los errores de validación
 */
data class FormErrors(
    val nameError: String? = null,
    val ageError: String? = null,
    val emailError: String? = null,
    val termsError: String? = null
)

/**
 * Data class para mostrar el resultado del registro
 */
data class RegistrationResult(
    val userName: String = "",
    val userAge: String = "",
    val userEmail: String = "",
    val userGender: String = "",
    val isUserActive: Boolean = false,
    val userLevel: Int = 0,
    val isSuccess: Boolean = false
)

