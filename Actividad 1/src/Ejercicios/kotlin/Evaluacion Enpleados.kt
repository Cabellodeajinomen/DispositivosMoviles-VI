/**
 * Descripcion: Calcula el nivel de rendimiento y beneficio economico
 * Determina si el empleado tiene rendimiento bajo, medio o alto segun su puntuacion
 * Calcula el bono economico como un porcentaje del salario mensual.
 * Autor: Alex Rhoddo P
 * Fecha creacion: 28 de marzo de 2026
 * Fecha ultima modificacion: 28 de marzo de 2026
 */

fun main() {
    // Solicitar y validar el salario mensual del empleado
    print("Ingrese su salario mensual: ")
    val salario = readlnOrNull()?.toDoubleOrNull()
    if (salario == null || salario < 0) {
        println("Salario no valido. Debe ser un numero positivo.")
        return
    }

    // Solicitar y validar la puntuacion de rendimiento (0-10)
    print("Ingrese su puntuacion de rendimiento (0-10): ")
    val puntuacion = readlnOrNull()?.toDoubleOrNull()
    if (puntuacion == null || puntuacion !in 0.0..10.0) {
        println("Puntuacion no valida. Debe estar entre 0 y 10.")
        return
    }

    // Evaluar el rendimiento del empleado
    evaluarEmpleado(salario, puntuacion)
}

fun evaluarEmpleado(salario: Double, puntuacion: Double) {
    val nivel = when {
        puntuacion in 0.0..3.0 -> "Nivel Bajo"
        puntuacion in 4.0..6.0 -> "Nivel Medio"
        puntuacion in 7.0..10.0 -> "Nivel Alto"
        else -> "Puntuacion no valida"
    }

    // Calcular el bono
    if (nivel != "Puntuacion no valida") {
        val bono = salario * (puntuacion / 10)
        println("Resultado: $nivel, Bono economico: \$${"%.2f".format(bono)}")
    } else {
        println(nivel)
    }
}