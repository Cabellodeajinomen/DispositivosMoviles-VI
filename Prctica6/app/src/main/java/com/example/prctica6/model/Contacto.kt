/**
 * Descripcion corta: Define el modelo de datos de un contacto para mostrarlo, marcarlo como favorito y eliminarlo.
 * Autor: Alex Rhoddo P
 * Fecha de creacion: 2026-05-03
 * Fecha de ultima modificacion: 2026-05-03
 */
package com.example.prctica6.model

// Representa un contacto de la lista con un identificador unico para actualizarlo con seguridad.
data class Contacto(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val favorito: Boolean
)

