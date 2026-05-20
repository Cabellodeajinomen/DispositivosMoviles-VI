# ColorGame

Juego de colores hecho con Kotlin, Jetpack Compose, Navigation Compose y MVVM.

## Autor
- Alex Rhoddo Pacheco

## Repositorio
- https://github.com/Cabellodeajinomen/DispositivosMoviles-VI.git

## Flujo
- `WelcomeScreen`: reglas y boton de inicio.
- `GameScreen`: color objetivo, botones de respuesta, timer de 30 segundos y puntaje.
- `ResultScreen`: puntaje final, mejor puntaje y historial de la sesion.

## Extra obligatoria
- Animacion simple del color objetivo con `animateColorAsState`.

## Persistencia
- `SharedPreferences` para guardar el mejor puntaje historico.

## Comandos utiles
```powershell
.\gradlew.bat :app:compileDebugKotlin
.\gradlew.bat :app:lintDebug
.\gradlew.bat :app:assembleDebug
```
