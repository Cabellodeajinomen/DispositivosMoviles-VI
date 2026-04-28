# Practica 2 - Programacion Orientada a Objetos Kotlin

Coleccion de 3 ejercicios en Kotlin para practicar clases, herencia, abstraccion, interfaces, validaciones y funciones.

## Objetivo

Aplicar fundamentos de Programacion Orientada a Objetos en programas de consola que resuelven problemas con manejo de errores basico.

## Contenido de la actividad

### Clase para Producto.kt

- Clase `Producto` con precio y descuento.
- Uso de metodos `set/get`.
- Validacion de datos en metodos `set`.
- Calculo del precio final con descuento.

### Figuras.kt

- Clase abstracta `Shape` con propiedades de area y perimetro.
- Subclases `Cuadrado`, `Circulo` y `Rectangulo`.
- Constructores secundarios para recibir dimensiones.
- Ejecucion de calculos de area y perimetro por instancia.

### Sistema de Gestion de Biblioteca.kt

- Sistema de biblioteca con clases e interfaz.
- Registro de materiales y usuarios.
- Prestamo y devolucion de materiales.
- Funcionalidad adicional de busqueda por titulo y por autor.

## Estructura del proyecto

```text
Actividad 2/
  Ejercicios/
    src/
      Clase para Producto.kt
      Figuras.kt
      Sistema de Gestion de Biblioteca.kt
    docs/
      rubrica-respuesta.md
    README.md
```

## Requisitos

- JDK 17 o superior
- Kotlin compatible con `require`
- IntelliJ IDEA (Community o Ultimate) o Android Studio

## Como ejecutar

### Opcion A: IDE (recomendada)

1. Abrir la carpeta `Actividad 2/Ejercicios` en IntelliJ IDEA o Android Studio.
2. Ir a `src/`.
3. Ejecutar el archivo `.kt` deseado (cada archivo tiene su propia funcion `main`).

### Opcion B: Terminal (PowerShell)

```powershell
kotlinc "src/Clase para Producto.kt" -include-runtime -d producto.jar
java -jar producto.jar

kotlinc "src/Figuras.kt" -include-runtime -d figuras.jar
java -jar figuras.jar

kotlinc "src/Sistema de Gestion de Biblioteca.kt" -include-runtime -d biblioteca.jar
java -jar biblioteca.jar
```

## Manejo de errores implementado

- Validaciones con `require` en constructores y metodos.
- Captura de excepciones en casos de datos invalidos.
- Control de operaciones no validas en prestamos y devoluciones.

## Evidencia de control de versiones

- Repositorio con Git y GitHub.
- Commits con descripcion de cambios.
- Historial disponible en la rama principal.

## Repositorio

- URL principal: https://github.com/Cabellodeajinomen/DispositivosMoviles-VI
- Carpeta de la actividad: https://github.com/Cabellodeajinomen/DispositivosMoviles-VI/tree/main/Actividad%202/Ejercicios

## Autor

- Alex Rhoddo P
- Fecha de creacion: 4 de abril de 2026
- Ultima actualizacion: 5 de abril de 2026
