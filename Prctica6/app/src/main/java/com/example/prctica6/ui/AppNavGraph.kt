/**
 * Descripcion corta: Construye el NavHost de la aplicacion y administra el estado local de contactos con remember.
 * Autor: Alex Rhoddo P
 * Fecha de creacion: 2026-05-03
 * Fecha de ultima modificacion: 2026-05-03
 */
package com.example.prctica6.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prctica6.model.Contacto
import com.example.prctica6.navigation.Rutas
import com.example.prctica6.ui.screens.FormularioScreen
import com.example.prctica6.ui.screens.ListaScreen

// Punto central de navegacion: aqui vive el estado local de la lista y las acciones sobre los contactos.
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    // Se usa remember para conservar la lista durante la recomposicion.
    // Nota: Para que el estado sobreviva a cambios de configuracion (como rotacion),
    // se recomienda usar un ViewModel o rememberSaveable.
    var contactos by remember { mutableStateOf(getInitialContacts()) }

    // Logica extraida para mejorar la legibilidad del NavHost.
    val handleEliminarContacto: (Int) -> Unit = { contactoId ->
        contactos = contactos.filterNot { it.id == contactoId }
    }

    val handleCambiarFavorito: (Int) -> Unit = { contactoId ->
        contactos = contactos.map { contacto ->
            if (contacto.id == contactoId) {
                contacto.copy(favorito = !contacto.favorito)
            } else {
                contacto
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Rutas.ListaContactos
    ) {
        composable(Rutas.ListaContactos) {
            ListaScreen(
                contactos = contactos,
                onAgregarContacto = {
                    navController.navigate(Rutas.FormularioContacto)
                },
                onEliminarContacto = handleEliminarContacto,
                onCambiarFavorito = handleCambiarFavorito
            )
        }

        composable(Rutas.FormularioContacto) {
            FormularioScreen(
                onGuardar = {
                    // El formulario solo regresa a la pantalla anterior, tal como indica la practica.
                    navController.popBackStack()
                }
            )
        }
    }
}

// Proporciona la lista inicial de contactos.
// Extraer esto mantiene el Composable principal enfocado en la navegacion.
private fun getInitialContacts() = listOf(
    Contacto(1, "Alex Rhoddo", "987 654 321", favorito = true),
    Contacto(2, "María López", "912 345 678", favorito = false),
    Contacto(3, "Carlos Pérez", "923 456 789", favorito = false),
    Contacto(4, "Sofía García", "934 567 890", favorito = true),
    Contacto(5, "Luis Martínez", "945 678 901", favorito = false)
)


