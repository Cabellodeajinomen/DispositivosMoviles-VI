/**
 * Descripcion: Juego de Piedra, Papel o Tijera vs la computadora
 * El usuario puede jugar multiples rondas y decidir si volver a jugar al final de cada ronda
 * La computadora elige una opcion de manera aleatoria
 *
 * Autor: Alex Rhoddo P
 * Fecha creacion: 28 de marzo de 2026
 * Fecha ultima modificacion: 28 de marzo de 2026
 */

fun main() {
    // Lista de opciones disponibles
    val opciones = listOf("piedra", "papel", "tijera")
    var seguirJugando = true

    println("Bienvenido a Piedra, Papel o Tijera")
    println()

    while (seguirJugando) {
        // Solicitar la opcion del usuario
        print("Ingrese su eleccion (piedra, papel o tijera): ")
        val eleccionUsuario = readlnOrNull()?.trim()?.lowercase()

        // Validar que la opcion sea valida
        if (eleccionUsuario == null || eleccionUsuario !in opciones) {
            println("Eleccion no valida. Intente nuevamente.")
            println()
            continue
        }

        // Generar la opcion aleatoria de la computadora
        val eleccionComputadora = opciones.random()
        println("La computadora eligio: $eleccionComputadora")

        // Resultado de la ronda
        val resultado = when {

            eleccionUsuario == eleccionComputadora -> "Empate"
            (eleccionUsuario == "piedra" && eleccionComputadora == "tijera") ||
                    (eleccionUsuario == "papel" && eleccionComputadora == "piedra") ||
                    (eleccionUsuario == "tijera" && eleccionComputadora == "papel") -> "¡Ganaste!"
            else -> "Perdiste"
        }

        println("Resultado: $resultado")

        // Preguntar si el usuario quiere volver a jugar
        print("Deseas volver a jugar? (si/no): ")
        val respuesta = readlnOrNull()?.trim()?.lowercase()
        seguirJugando = respuesta == "si" || respuesta == "s"
        println()
    }

    println("Juego finalizado. Gracias por jugar.")
}

