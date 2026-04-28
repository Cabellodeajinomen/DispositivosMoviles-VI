/**
 * Descripcion: Jerarquia de figuras con clase abstracta Shape
 * Incluye subclases Cuadrado, Circulo y Rectangulo para calcular area y perimetro
 * Autor: Alex Rhoddo P
 * Fecha creacion: 04 de abril de 2026
 * Fecha ultima modificacion: 04 de abril de 2026
 */

import kotlin.math.PI

abstract class Shape {
    var area: Double = 0.0
        protected set

    var perimetro: Double = 0.0
        protected set

    abstract fun calcularArea(): Double
    abstract fun calcularPerimetro(): Double

    fun imprimirResultados() {
        println("${this::class.simpleName}: area = $area, perimetro = $perimetro")
    }
}

class Cuadrado : Shape {
    private var lado: Double

    constructor(lado: Double) {
        require(lado > 0.0) { "El lado debe ser mayor a cero." }
        this.lado = lado
    }

    override fun calcularArea(): Double {
        area = lado * lado
        return area
    }

    override fun calcularPerimetro(): Double {
        perimetro = 4 * lado
        return perimetro
    }
}

class Circulo : Shape {
    private var radio: Double

    constructor(radio: Double) {
        require(radio > 0.0) { "El radio debe ser mayor que cero." }
        this.radio = radio
    }

    override fun calcularArea(): Double {
        area = PI * radio * radio
        return area
    }

    override fun calcularPerimetro(): Double {
        perimetro = 2 * PI * radio
        return perimetro
    }
}

class Rectangulo : Shape {
    private var base: Double
    private var altura: Double

    constructor(base: Double, altura: Double) {
        require(base > 0.0) { "La base debe ser mayor a cero." }
        require(altura > 0.0) { "La altura debe ser mayor a cero." }
        this.base = base
        this.altura = altura
    }

    override fun calcularArea(): Double {
        area = base * altura
        return area
    }

    override fun calcularPerimetro(): Double {
        perimetro = 2 * (base + altura)
        return perimetro
    }
}

private fun ejecutarOperaciones(figura: Shape) {
    figura.calcularArea()
    figura.calcularPerimetro()
    figura.imprimirResultados()
}

fun main() {
    // Crear instancias de figuras con datos validos
    val figuras: List<Shape> = listOf(
        Cuadrado(5.0),
        Circulo(3.0),
        Rectangulo(4.0, 6.0)
    )

    // Ejecutar operaciones de area y perimetro
    figuras.forEach(::ejecutarOperaciones)

    // Probar validacion de dato invalido
    try {
        val figuraInvalida = Circulo(-2.0)
        ejecutarOperaciones(figuraInvalida)
    } catch (e: IllegalArgumentException) {
        println("Error de validacion en figura: ${e.message}")
    }
}
