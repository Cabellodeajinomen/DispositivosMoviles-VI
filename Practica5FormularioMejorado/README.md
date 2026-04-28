# Practica 5 - Formulario Mejorado con Jetpack Compose

Autor: Alex Rhoddo P.

Aplicacion Android en Kotlin para practicar validaciones y componentes de Jetpack Compose en un formulario interactivo.

## Objetivo
Implementar un formulario mejorado con validaciones en tiempo real, componentes avanzados (RadioButton, Checkbox, Switch, Slider) y manejo de errores.

## Funcionalidades implementadas
- Campos obligatorios: nombre, edad, correo con validaciones.
- Componentes: RadioButton para genero, Checkbox para terminos, Switch para estado activo, Slider para nivel.
- Validaciones en tiempo real con mensajes de error debajo de cada campo.
- Boton "Registrar" inteligente deshabilitado si hay errores.
- Boton "Limpiar" que reinicia todos los campos.
- Tarjeta de resultado con fondo verde cuando el registro es exitoso.
- Extras: Contador de caracteres en nombre, mensajes de error contextuales.

## Estructura del proyecto
- `app/src/main/java/com/example/practica5formulariomejorado/MainActivity.kt`
- `app/src/main/java/com/example/practica5formulariomejorado/model/FormData.kt`
- `app/src/main/java/com/example/practica5formulariomejorado/util/FormValidator.kt`
- `app/src/main/java/com/example/practica5formulariomejorado/ui/components/FormComponents.kt`
- `app/src/main/java/com/example/practica5formulariomejorado/ui/screens/ImprovedFormScreen.kt`

## Como ejecutar
1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar en emulador o dispositivo con API 33+.

## Flujo de uso
1. Completar el formulario con nombre, edad y correo.
2. Seleccionar genero, marcar terminos.
3. El boton se habilita automaticamente cuando todo es valido.
4. Presionar "Registrar" para ver el resultado en tarjeta verde.
5. Presionar "Limpiar" para reiniciar el formulario.

## Repositorio GitHub
- Enlace del repositorio: `https://github.com/Cabellodeajinomen/DispositivosMoviles-VI.git`