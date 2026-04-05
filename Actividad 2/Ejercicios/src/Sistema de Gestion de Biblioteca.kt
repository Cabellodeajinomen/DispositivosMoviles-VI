  /**
   * Descripcion: Sistema de gestion de biblioteca con clases e interfaz
   * Permite registrar materiales y usuarios, ademas de gestionar prestamos y devoluciones
   * Autor: Alex Rhoddo P
   * Fecha creacion: 04 de abril de 2026
   * Fecha ultima modificacion: 05 de abril de 2026
   */

abstract class Material(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int
) {
    init {
        require(titulo.isNotBlank()) { "El titulo no puede estar vacio." }
        require(autor.isNotBlank()) { "El autor no puede estar vacio." }
        require(anioPublicacion > 0) { "El anio de publicacion debe ser mayor que cero." }
    }

    abstract fun mostrarDetalles(): String
}

class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val genero: String,
    val numeroPaginas: Int
) : Material(titulo, autor, anioPublicacion) {
    init {
        require(genero.isNotBlank()) { "El genero no puede estar vacio." }
        require(numeroPaginas > 0) { "El numero de paginas debe ser mayor a cero." }
    }

    override fun mostrarDetalles(): String {
        return "Libro -> titulo: $titulo, autor: $autor, anio: $anioPublicacion, genero: $genero, paginas: $numeroPaginas"
    }
}

class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val issn: String,
    val volumen: Int,
    val numero: Int,
    val editorial: String
) : Material(titulo, autor, anioPublicacion) {
    init {
        require(issn.isNotBlank()) { "El ISSN no puede estar vacio." }
        require(volumen > 0) { "El volumen debe ser mayor a cero." }
        require(numero > 0) { "El numero debe ser mayor a cero." }
        require(editorial.isNotBlank()) { "La editorial no puede estar vacia." }
    }

    override fun mostrarDetalles(): String {
        return "Revista -> titulo: $titulo, autor: $autor, anio: $anioPublicacion, issn: $issn, volumen: $volumen, numero: $numero, editorial: $editorial"
    }
}

data class Usuario(
    val nombre: String,
    val apellido: String,
    val edad: Int
) {
    init {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacio." }
        require(apellido.isNotBlank()) { "El apellido no puede estar vacio." }
        require(edad in 1..120) { "La edad debe estar entre 1 y 120." }
    }
}

interface IBiblioteca {
    fun registrarMaterial(material: Material)
    fun registrarUsuario(usuario: Usuario)
    fun prestarMaterial(usuario: Usuario, titulo: String): Boolean
    fun devolverMaterial(usuario: Usuario, titulo: String): Boolean
    fun mostrarMaterialesDisponibles()
    fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario)
}

class Biblioteca : IBiblioteca {
    private val materialesDisponibles = mutableListOf<Material>()
    private val prestamosPorUsuario = mutableMapOf<Usuario, MutableList<Material>>()

    override fun registrarMaterial(material: Material) {
        val existe = materialesDisponibles.any { it.titulo.equals(material.titulo, ignoreCase = true) }
        if (!existe) {
            materialesDisponibles.add(material)
        }
    }

    override fun registrarUsuario(usuario: Usuario) {
        prestamosPorUsuario.putIfAbsent(usuario, mutableListOf())
    }

    override fun prestarMaterial(usuario: Usuario, titulo: String): Boolean {
        val materialesPrestados = prestamosPorUsuario[usuario] ?: return false
        val material = materialesDisponibles.firstOrNull { it.titulo.equals(titulo, ignoreCase = true) } ?: return false

        materialesDisponibles.remove(material)
        materialesPrestados.add(material)
        return true
    }

    override fun devolverMaterial(usuario: Usuario, titulo: String): Boolean {
        val materialesPrestados = prestamosPorUsuario[usuario] ?: return false
        val material = materialesPrestados.firstOrNull { it.titulo.equals(titulo, ignoreCase = true) } ?: return false

        materialesPrestados.remove(material)
        materialesDisponibles.add(material)
        return true
    }

    override fun mostrarMaterialesDisponibles() {
        println("Materiales disponibles:")
        if (materialesDisponibles.isEmpty()) {
            println("- No hay materiales disponibles.")
            return
        }

        materialesDisponibles.forEach { println("- ${it.mostrarDetalles()}") }
    }

    override fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario) {
        println("Materiales reservados por ${usuario.nombre} ${usuario.apellido}:")
        val lista = prestamosPorUsuario[usuario]
        if (lista.isNullOrEmpty()) {
            println("- No tiene prestamos activos.")
            return
        }

        lista.forEach { println("- ${it.mostrarDetalles()}") }
    }

    // Funcionalidad adicional para buscar por coincidencia parcial de titulo
    fun buscarMaterialPorTitulo(texto: String): List<Material> {
        val disponibles = materialesDisponibles
        val prestados = prestamosPorUsuario.values.flatten()
        return (disponibles + prestados)
            .distinctBy { it.titulo.lowercase() }
            .filter { it.titulo.contains(texto, ignoreCase = true) }
    }

    // Funcionalidad adicional para filtrar materiales por autor
    fun buscarMaterialPorAutor(texto: String): List<Material> {
        val disponibles = materialesDisponibles
        val prestados = prestamosPorUsuario.values.flatten()
        return (disponibles + prestados)
            .distinctBy { "${it.titulo.lowercase()}-${it.autor.lowercase()}" }
            .filter { it.autor.contains(texto, ignoreCase = true) }
    }
}

fun main() {
    val biblioteca = Biblioteca()

    val usuario1 = Usuario("Ana", "Lopez", 21)
    val usuario2 = Usuario("Luis", "Perez", 24)

    biblioteca.registrarUsuario(usuario1)
    biblioteca.registrarUsuario(usuario2)

    val libro1 = Libro("Clean Code", "Robert C. Martin", 2008, "Tecnico", 464)
    val libro2 = Libro("1984", "George Orwell", 1949, "Novela", 328)
    val revista1 = Revista("National Geographic", "Varios", 2024, "1234-5678", 40, 6, "NatGeo")

    biblioteca.registrarMaterial(libro1)
    biblioteca.registrarMaterial(libro2)
    biblioteca.registrarMaterial(revista1)

    biblioteca.mostrarMaterialesDisponibles()

    println("\nPrestamo de '1984' para Ana: ${biblioteca.prestarMaterial(usuario1, "1984")}")
    println("Prestamo de 'National Geographic' para Luis: ${biblioteca.prestarMaterial(usuario2, "National Geographic")}")
    println("Prestamo de titulo inexistente para Ana: ${biblioteca.prestarMaterial(usuario1, "Titulo No Existe")}")

    println()
    biblioteca.mostrarMaterialesDisponibles()

    println()
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario1)
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario2)

    println("\nDevolucion de '1984' por Ana: ${biblioteca.devolverMaterial(usuario1, "1984")}")
    println("Devolucion repetida de '1984' por Ana: ${biblioteca.devolverMaterial(usuario1, "1984")}")

    println()
    biblioteca.mostrarMaterialesDisponibles()

    println("\nBusqueda adicional por titulo con 'code':")
    biblioteca.buscarMaterialPorTitulo("code").forEach { println("- ${it.mostrarDetalles()}") }

    println("\nBusqueda adicional por autor con 'orwell':")
    biblioteca.buscarMaterialPorAutor("orwell").forEach { println("- ${it.mostrarDetalles()}") }
}
