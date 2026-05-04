/**
 * Descripcion corta: Muestra la lista dinamica de contactos, permite alternar favoritos y eliminar registros.
 * Autor: Alex Rhoddo P
 * Fecha de creacion: 2026-05-03
 * Fecha de ultima modificacion: 2026-05-03
 */
package com.example.prctica6.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prctica6.model.Contacto

// Pantalla principal que ordena los favoritos primero y expone las acciones sobre cada contacto.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(
    contactos: List<Contacto>,
    onAgregarContacto: () -> Unit,
    onEliminarContacto: (Int) -> Unit,
    onCambiarFavorito: (Int) -> Unit
) {
    // Usamos remember para evitar reordenar la lista en cada recomposicion innecesaria.
    val contactosOrdenados = remember(contactos) {
        contactos.sortedByDescending { it.favorito }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Contactos favoritos") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarContacto) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ir al formulario"
                )
            }
        }
    ) { innerPadding ->
        if (contactosOrdenados.isEmpty()) {
            EmptyContactsState(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(contactosOrdenados, key = { it.id }) { contacto ->
                    ContactoItem(
                        contacto = contacto,
                        onEliminarContacto = onEliminarContacto,
                        onCambiarFavorito = onCambiarFavorito
                    )
                }
            }
        }
    }
}

// Tarjeta reutilizable para mostrar la informacion y las acciones de un contacto.
@Composable
private fun ContactoItem(
    contacto: Contacto,
    onEliminarContacto: (Int) -> Unit,
    onCambiarFavorito: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = contacto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = contacto.telefono,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (contacto.favorito) {
                    Text(
                        text = "Favorito ⭐",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            IconButton(
                onClick = { onCambiarFavorito(contacto.id) }
            ) {
                Icon(
                    imageVector = if (contacto.favorito) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = if (contacto.favorito) {
                        "Quitar de favoritos"
                    } else {
                        "Marcar como favorito"
                    },
                    tint = if (contacto.favorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(
                onClick = { onEliminarContacto(contacto.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar contacto",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

// Estado visual simple para cuando todavia no existen contactos en la lista.
@Composable
private fun EmptyContactsState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No hay contactos disponibles",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Usa el botón + para abrir el formulario.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}



