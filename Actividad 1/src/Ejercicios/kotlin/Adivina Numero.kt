/**
 * Descripcion: Juego para adivinar un numero entre 1 y 30
 * El usuario tiene 5 intentos para adivinar el numero secreto
 * Se proporcionan pistas indicando si el numero si es mayor o menor
 * Autor: Alex Rhoddo P
 * Fecha creacion: 29 de marzo de 2026
 * Fecha ultima modificacion: 29 de marzo de 2026
 */
fun main() {
    // Numero aleatorio entre 1 y 30
    val numeroSecreto = (1..30).random()
    var intentosRestantes = 5
    var adivinado = false

    println("Bienvenido al juego: Adivina el Numero")
    println("Adivina el numero entre 1 y 30.")
    println("Tienes $intentosRestantes intentos para adivinarlo")
    println()

    while (intentosRestantes > 0 && !adivinado) {
        print("Ingresa tu intento: ")
        val intento = readlnOrNull()?.trim()?.toIntOrNull()

        // Validar que el intento sea un numero valido en el rango
        if (intento == null || intento !in 1..30) {
            println("Entrada no valida. Ingresa un numero entre 1 y 30")
            println()
            continue
        }

        // Verificar si el numero fue adivinado
        if (intento == numeroSecreto) {
            adivinado = true
            println("Ganaste el juego.")
            println("El numero secreto era: $numeroSecreto")
        } else {
            // Disminuee intentos si el numero no es correcto
            intentosRestantes--

            // Mostrar mensaje si ya no hay intentps
            if (intentosRestantes == 0) {
                println("Game over. Se acabaron tus intentos")
                println("El numero secreto era: $numeroSecreto")
            } else {
                // Proporcionar pista sobre si el numero es mayor o menor
                if (intento < numeroSecreto) {
                    println("Pista: El numero secreto es MAYOR que $intento.")
                } else {
                    println("Pista: El numero secreto es MENOR que $intento.")
                }

                // Mostrar intentos restantes
                println("Intentos restantes: $intentosRestantes")
                println()
            }
        }
    }
}