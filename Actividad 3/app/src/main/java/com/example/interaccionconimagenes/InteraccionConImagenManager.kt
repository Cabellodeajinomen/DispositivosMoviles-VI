package com.example.interaccionconimagenes

import android.view.View

/**
 * Descripcion: Configura la interaccion sobre una imagen para ejecutar una accion al hacer clic.
 * Autor: Alex Rhoddo Pacheco
 * Fecha de creacion: 2026-04-12
 * Fecha de ultima modificacion: 2026-04-12
 */
class InteraccionConImagenManager {
    fun attachClickAction(targetView: View, onImageClick: () -> Unit) {
        targetView.setOnClickListener { onImageClick() }
    }
}


