/*
 * Descripcion: Modelo serializable para transportar datos del perfil entre actividades.
 * Autor: Alex Rhoddo P.
 * Fecha de creacion: 26-04-2026
 * Fecha de ultima modificacion: 27-04-2026
 */

package com.example.practica5formulariomejorado.util

import com.example.practica5formulariomejorado.model.FormData
import com.example.practica5formulariomejorado.model.FormErrors

// Objeto con funciones de validacion para el formulario
// Implementa todas las reglas de validacion requeridas
object FormValidator {

    // Valida el nombre del usuario
    fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "El nombre es obligatorio"
            name.length < 3 -> "El nombre debe tener al menos 3 caracteres"
            else -> null
        }
    }

    // Valida la edad del usuario
    fun validateAge(age: String): String? {
        return when {
            age.isBlank() -> "La edad es obligatoria"
            !age.all { it.isDigit() } -> "La edad debe contener solo numeros"
            age.toIntOrNull() == null -> "La edad no es un numero valido"
            age.toInt() < 18 -> "Debes ser mayor de 18 años"
            age.toInt() > 120 -> "La edad debe ser realista"
            else -> null
        }
    }

    // Valida el correo electronico
    fun validateEmail(email: String): String? {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return when {
            email.isBlank() -> "El correo es obligatorio"
            !email.contains("@") -> "El correo debe contener @"
            !emailRegex.matches(email) -> "El correo debe ser valido (ej: usuario@dominio.com)"
            else -> null
        }
    }

    // Valida los terminos aceptados
    fun validateTerms(acceptTerms: Boolean): String? {
        return if (!acceptTerms) "Debes aceptar los terminos y condiciones" else null
    }

    // Valida el formulario completo
    fun validateForm(formData: FormData): FormErrors {
        return FormErrors(
            nameError = validateName(formData.name),
            ageError = validateAge(formData.age),
            emailError = validateEmail(formData.email),
            termsError = validateTerms(formData.acceptTerms)
        )
    }

    // Verifica si el formulario es valido
    fun isFormValid(errors: FormErrors): Boolean {
        return errors.nameError == null &&
                errors.ageError == null &&
                errors.emailError == null &&
                errors.termsError == null
    }
}


