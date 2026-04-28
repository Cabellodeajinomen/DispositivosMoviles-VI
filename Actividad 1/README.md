# Actividad 1 - Kotlin

Coleccion de 4 ejercicios en Kotlin para practicar entrada por consola, estructuras de control, validaciones y funciones.

## Objetivo

Aplicar fundamentos de Kotlin en programas de consola que resuelven problemas simples con manejo de errores basico.

## Contenido de la actividad

1. **Calculador Elemental.kt**
   - Operaciones: suma, resta, multiplicacion y division.
   - Menu interactivo con ciclo principal.
   - Validacion de entradas numericas.
   - Control de division entre cero.

2. **Evaluacion Enpleados.kt**
   - Solicita salario y puntuacion de rendimiento (0 a 10).
   - Clasifica nivel: Bajo, Medio o Alto.
   - Calcula bono economico con base en la puntuacion.
   - Valida rangos y tipo de dato.

3. **Piedra, Papel o Tijera.kt**
   - Juego contra la computadora con eleccion aleatoria.
   - Permite multiples rondas.
   - Valida entradas de usuario.
   - Muestra resultado: empate, ganaste o perdiste.

4. **Adivina Numero.kt**
   - Genera un numero secreto aleatorio entre 1 y 30.
   - Da 5 intentos maximos.
   - Muestra pistas (mayor/menor).
   - Finaliza con mensaje de victoria o game over.

## Estructura del proyecto

```text
Actividad 1/
  src/
    Ejercicios/
      kotlin/
        Adivina Numero.kt
        Calculador Elemental.kt
        Evaluacion Enpleados.kt
        Piedra, Papel o Tijera.kt
    test/
```

## Requisitos

- JDK 17 o superior
- Kotlin compatible con `readlnOrNull()`
- IntelliJ IDEA (Community o Ultimate) o Android Studio

## Como ejecutar

### Opcion A: IDE (recomendada)

1. Abrir la carpeta `Actividad 1` en IntelliJ IDEA o Android Studio.
2. Ir a `src/Ejercicios/kotlin/`.
3. Ejecutar el archivo `.kt` deseado (cada archivo tiene su propia funcion `main`).

## Manejo de errores implementado

- Lecturas seguras con `readlnOrNull()`.
- Conversion segura con `toIntOrNull()` y `toDoubleOrNull()`.
- Validacion de rangos numericos (ejemplo: 1..30 y 0..10).
- Mensajes cuando la entrada es invalida.
- Prevencion de division entre cero.

## Evidencia de control de versiones

- Repositorio con Git y GitHub.
- Commits con descripcion de cambios.
- Historial disponible en la rama principal.

## Repositorio

- URL principal: https://github.com/Cabellodeajinomen/DispositivosMoviles-VI
- Carpeta de la actividad: https://github.com/Cabellodeajinomen/DispositivosMoviles-VI/tree/main/Actividad%201

## Autor

- Alex Rhoddo P
- Fecha de creacion: 28 de marzo de 2026
- Ultima actualizacion: 29 de marzo de 2026
