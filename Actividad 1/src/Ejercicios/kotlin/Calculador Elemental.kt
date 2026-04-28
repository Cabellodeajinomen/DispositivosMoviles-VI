/**
 * Descripcion: Calculadora elemental
 * Permite al usuario elegir entre suma, resta, multiplicacion, division o salir
 * La aplicacion se ejecuta en un bucle hasta que el usuario seleccione salir
 *
 * Autor: Alex Rhoddo P
 * Fecha creacion: 28 de marzo de 2026
 * Fecha ultima modificacion: 28 de marzo de 2026
 */

fun main() {
    // Bandera para controlar si continua el programa
    var ejecutando = true

    while (ejecutando) {
        mostrarMenu()
        print("Seleccione una opcion (1-5): ")
        val opcion = readlnOrNull()?.trim()?.toIntOrNull()

        // Opcion seleccionada
        when (opcion) {
            // (suma, resta, multiplicacion, division)
            1, 2, 3, 4 -> {
                val primerNumero = leerNumero("Ingrese el primer numero: ")
                val segundoNumero = leerNumero("Ingrese el segundo numero: ")

                // Validacion de numeros ingresados
                if (primerNumero == null || segundoNumero == null) {
                    println("Entrada no valida. Debe ingresar numeros")
                    println()
                    continue
                }

                // Ejecucion de operacion seleccionada
                val resultado = when (opcion) {
                    1 -> sumar(primerNumero, segundoNumero)
                    2 -> restar(primerNumero, segundoNumero)
                    3 -> multiplicar(primerNumero, segundoNumero)
                    4 -> {
                        // Validacion para division entre cero
                        if (segundoNumero == 0.0) {
                            println("Error: No se puede dividir entre cero")
                            println()
                            continue
                        }
                        dividir(primerNumero, segundoNumero)
                    }
                    else -> 0.0
                }

                println("Resultado: $resultado")
                println()
            }

            // Opcion para salir
            5 -> {
                println("Saliendo de la calculadora. Hasta luego")
                ejecutando = false
            }

            // Opcion no valida
            else -> {
                println("Opcion no disponible. Intente nuevamente")
                println()
            }
        }
    }
}

//Muestra el menu de opciones disponibles en la calculadora
fun mostrarMenu() {
    println("CALCULADORA ELEMENTAL ")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicacion")
    println("4. Division")
    println("5. Salir")
}

//El mensaje a mostrar al usuario
//El numero leido, o null si la entrada no es valida

fun leerNumero(mensaje: String): Double? {
    print(mensaje)
    return readlnOrNull()?.trim()?.toDoubleOrNull()
}
//a es el primer numero, b es el segundo numero

//Suma dos numeros
fun sumar(a: Double, b: Double): Double = a + b

//La diferencia de a y b
fun restar(a: Double, b: Double): Double = a - b

//El producto de a y b
fun multiplicar(a: Double, b: Double): Double = a * b

//Divide el primer numero entre el segundo
fun dividir(a: Double, b: Double): Double = a / b

