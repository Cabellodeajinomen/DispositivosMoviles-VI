/*
 * Descripcion: Modelo serializable para transportar datos del perfil entre actividades.
 * Autor: Alex Rhoddo P.
 * Fecha de creacion: 19-04-2026
 * Fecha de ultima modificacion: 19-04-2026
 */
package com.example.prctica4_comunicacinentreactividades.model

import java.io.Serializable

data class Usuario(
    val nombre: String,
    val edad: Int,
    val ciudad: String,
    val correo: String
) : Serializable

