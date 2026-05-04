# Práctica 6 - Navegación y Listas en Jetpack Compose

Aplicación Android desarrollada en **Kotlin** con **Jetpack Compose** para gestionar contactos con favoritos usando navegación entre pantallas.

## Autor
**Alex Rhoddo P**

## Repositorio de GitHub
https://github.com/Cabellodeajinomen/DispositivosMoviles-VI.git

## Descripción
Esta práctica 6 se implementa un gestor de contactos con las siguientes funciones:

- Lista dinámica de contactos con `LazyColumn`.
- Marcado y desmarcado de favoritos con icono de estrella.
- Eliminación de contactos.
- Reordenamiento automático para mostrar primero los favoritos.
- Navegación entre la pantalla de lista y la pantalla de formulario.
- Manejo de estado con `remember`, sin usar `ViewModel`.
- Validación visual de campos para avisar cuando se escriben caracteres no permitidos.

## Estructura principal

- `MainActivity.kt`: punto de entrada de la app.
- `ui/AppNavGraph.kt`: navegación principal con `NavHost` y `NavController`.
- `ui/screens/ListaScreen.kt`: pantalla de lista de contactos.
- `ui/screens/FormularioScreen.kt`: pantalla de formulario.
- `model/Contacto.kt`: modelo de datos del contacto.
- `navigation/Rutas.kt`: rutas de navegación.
- `ui/theme/*`: configuración visual de Material 3.

## Cómo ejecutar el proyecto

1. Abrir el proyecto en **Android Studio**.
2. Esperar a que Gradle sincronice las dependencias.
3. Ejecutar la aplicación en un emulador o dispositivo físico.

## Validación en el formulario

- El campo **Nombre** acepta solo letras y espacios.
- El campo **Teléfono** acepta solo números.
- Cuando se escriben caracteres inválidos, la app muestra un aviso en pantalla y deshabilita el botón **Guardar** hasta corregirlos.



