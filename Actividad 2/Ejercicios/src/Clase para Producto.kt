/**
 * Descripcion: Clase Producto con validacion de precio y descuento
 * Permite establecer datos con metodos set/get y calcular el precio final
 * Autor: Alex Rhoddo P
 * Fecha creacion: 04 de abril de 2026
 * Fecha ultima modificacion: 04 de abril de 2026
 */

class Producto {
    private var precio: Double = 0.0
    private var descuento: Double = 0.0

    fun setPrecio(nuevoPrecio: Double) {
        require(nuevoPrecio >= 0.0) { "El precio no puede ser negativo." }
        precio = nuevoPrecio
    }

    fun getPrecio(): Double = precio

    fun setDescuento(nuevoDescuento: Double) {
        require(nuevoDescuento in 0.0..100.0) { "El descuento debe estar entre 0 y 100." }
        descuento = nuevoDescuento
    }

    fun getDescuento(): Double = descuento

    fun calcularPrecioFinal(): Double {
        return precio - (precio * descuento / 100.0)
    }
}

fun main() {
    val producto = Producto()

    // Registrar valores validos del producto
    producto.setPrecio(150.0)
    producto.setDescuento(20.0)

    println("Precio original: ${producto.getPrecio()}")
    println("Descuento: ${producto.getDescuento()}%")
    println("Precio final: ${producto.calcularPrecioFinal()}")

    // Probar validacion de descuento fuera de rango
    try {
        producto.setDescuento(120.0)
    } catch (e: IllegalArgumentException) {
        println("Error de validacion de descuento: ${e.message}")
    }

    // Probar validacion de precio negativo
    try {
        producto.setPrecio(-50.0)
    } catch (e: IllegalArgumentException) {
        println("Error de validacion de precio: ${e.message}")
    }
}
