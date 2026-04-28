package com.example.practica5formulariomejorado.util

import com.example.practica5formulariomejorado.model.FormData
import com.example.practica5formulariomejorado.model.FormErrors

/**
 * Objeto con funciones de validación para el formulario
 * Implementa todas las reglas de validación requeridas
 */
object FormValidator {

    /**
     * Valida el nombre del usuario
     * @param name Nombre del usuario
     * @return Mensaje de error o null si es válido
     */
    fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "El nombre es obligatorio"
            name.length < 3 -> "El nombre debe tener al menos 3 caracteres"
            else -> null
        }
    }

    /**
     * Valida la edad del usuario
     * @param age Edad del usuario como string
     * @return Mensaje de error o null si es válido
     */
    fun validateAge(age: String): String? {
        return when {
            age.isBlank() -> "La edad es obligatoria"
            !age.all { it.isDigit() } -> "La edad debe contener solo números"
            age.toIntOrNull() == null -> "La edad no es un número válido"
            age.toInt() < 18 -> "Debes ser mayor de 18 años"
            age.toInt() > 120 -> "La edad debe ser realista"
            else -> null
        }
    }

    /**
     * Valida el correo electrónico
     * @param email Correo electrónico del usuario
     * @return Mensaje de error o null si es válido
     */
    fun validateEmail(email: String): String? {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return when {
            email.isBlank() -> "El correo es obligatorio"
            !email.contains("@") -> "El correo debe contener @"
            !emailRegex.matches(email) -> "El correo debe ser válido (ej: usuario@dominio.com)"
            else -> null
        }
    }

    /**
     * Valida los términos aceptados
     * @param acceptTerms Si el usuario aceptó los términos
     * @return Mensaje de error o null si es válido
     */
    fun validateTerms(acceptTerms: Boolean): String? {
        return if (!acceptTerms) "Debes aceptar los términos y condiciones" else null
    }

    /**
     * Valida el formulario completo
     * @param formData Datos del formulario a validar
     * @return Objeto FormErrors con todos los errores encontrados
     */
    fun validateForm(formData: FormData): FormErrors {
        return FormErrors(
            nameError = validateName(formData.name),
            ageError = validateAge(formData.age),
            emailError = validateEmail(formData.email),
            termsError = validateTerms(formData.acceptTerms)
        )
    }

    /**
     * Verifica si el formulario es válido
     * @param errors Objeto FormErrors con los errores
     * @return true si no hay errores, false en caso contrario
     */
    fun isFormValid(errors: FormErrors): Boolean {
        return errors.nameError == null &&
                errors.ageError == null &&
                errors.emailError == null &&
                errors.termsError == null
    }
}

