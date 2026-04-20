# Practica 4 - Comunicacion entre Actividades

Autor: Alex Rhoddo P.

Aplicacion Android en Kotlin para practicar comunicacion entre Activities con `Intent`, `Serializable`, `registerForActivityResult`, `setResult` y `onSaveInstanceState`.

## Objetivo
Implementar un editor de perfil con dos pantallas:
1. `FormularioActivity`: captura nombre, edad, ciudad y correo.
2. `ResumenActivity`: muestra datos y permite confirmar o volver a editar.

## Funcionalidades implementadas
- Envio de objeto `Usuario` por `Intent` usando `Serializable`.
- Retorno de resultado desde `ResumenActivity` a `FormularioActivity` con `setResult`.
- Manejo de resultado con `registerForActivityResult`.
- Persistencia de campos del formulario al rotar pantalla con `onSaveInstanceState`.
- Validaciones de formulario (campos obligatorios, rango de edad, formato de correo).
- Funcionalidad extra: boton `Limpiar` para borrar el formulario rapido.

## Estructura del proyecto
- `app/src/main/java/com/example/prctica4_comunicacinentreactividades/activities/FormularioActivity.kt`
- `app/src/main/java/com/example/prctica4_comunicacinentreactividades/activities/ResumenActivity.kt`
- `app/src/main/java/com/example/prctica4_comunicacinentreactividades/model/Usuario.kt`
- `app/src/main/res/layout/activity_formulario.xml`
- `app/src/main/res/layout/activity_resumen.xml`
- `app/src/main/res/values/strings.xml`

## Como ejecutar
1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar en emulador o dispositivo con API 33+.

## Flujo de uso
1. Completar los cuatro campos del formulario.
2. Presionar `Continuar` para ver el resumen.
3. Presionar `Confirmar` para regresar y mostrar el Toast de guardado.
4. Presionar `Volver a editar` para regresar sin guardar.

## Repositorio GitHub
- Enlace del repositorio: `https://github.com/Cabellodeajinomen/DispositivosMoviles-VI.git`



