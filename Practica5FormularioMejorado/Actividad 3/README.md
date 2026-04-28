# Actividad 3 - Aplicacion Basica Android (Ejercicio Integrado)

## Descripcion
Proyecto Android en Kotlin resuelto como **un solo ejercicio integrado**.

La app combina en una sola pantalla la **interaccion con imagen** (Toast al tocar la portada) y el **reproductor de musica** (play/pausa, siguiente y detener) usando `MediaPlayer` y audios locales en `res/raw`.

## Funcionalidades implementadas
- Cambio de portada y nombre segun la cancion actual.
- Boton unico de reproducir/pausar (`▶` / `⏸`).
- Boton de siguiente (`⏭`) para avanzar en la lista.
- Boton de detener (`⏹`) para volver al inicio.
- Mensajes de estado: listo, reproduciendo, pausado y detenido.

## Estructura principal
- `app/src/main/java/com/example/interaccionconimagenes/MainActivity.kt`
- `app/src/main/java/com/example/interaccionconimagenes/InteraccionConImagenManager.kt`
- `app/src/main/java/com/example/interaccionconimagenes/ReproductorDeMusicaController.kt`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/raw/justin_bieber_confident.mp3`
- `app/src/main/res/raw/the_weeknd_ariana_grande_die_for_you.mp3`
- `app/src/main/res/drawable-nodpi/confident.jpg`
- `app/src/main/res/drawable-nodpi/die_for_you.jpg`

## Requisitos
- Android Studio (version reciente estable).
- JDK 11.
- Dispositivo o emulador con Android **API 26 o superior**.

## Como ejecutar
1. Abrir la carpeta del proyecto en Android Studio.
2. Esperar la sincronizacion de Gradle.
3. Seleccionar un emulador/dispositivo con API 26+.
4. Ejecutar la configuracion `app`.

## Repositorio GitHub

`https://github.com/Cabellodeajinomen/DispositivosMoviles-VI.git`

## Autor
Alex Rhoddo Pacheco

