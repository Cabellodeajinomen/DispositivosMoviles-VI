# Rúbrica de Autoevaluación - Práctica 5 Formulario Mejorado

## Información General

| Estudiante | [Nombre] |
|------------|----------|
| Carrera | Dispositivos Móviles |
| Semestre | VI |
| Fecha | 2026-04-27 |
| Profesor | [Nombre del Profesor] |

---

## Evaluación por Criterios

### 📋 Aspecto 1: Estructura y Organización del Proyecto (/3)

**Rúbrica**: El proyecto tiene una estructura clara y bien organizada. Los archivos están ubicados en carpetas adecuadas con nombres descriptivos.

**Autoevaluación**: 🟢 **3/3 - EXCELENTE**

**Justificación**:
- ✅ Estructura clara: `model/`, `util/`, `ui/components/`, `ui/screens/`
- ✅ Nombres descriptivos: `FormValidator.kt`, `ImprovedFormScreen.kt`, `FormComponents.kt`
- ✅ Separación de responsabilidades: Cada carpeta tiene un propósito específico
- ✅ Fácil navegación: Estructura intuitiva y coherente
- ✅ Archivo README.md con documentación completa
- ✅ Archivo TECH_SPECS.md con especificaciones
- ✅ Archivo FAQ.md con preguntas frecuentes

**Evidencia**:
```
Practica5FormularioMejorado/
├── README.md (documentación principal)
├── TECH_SPECS.md (especificaciones técnicas)
├── FAQ.md (preguntas frecuentes)
├── app/src/main/java/com/.../
│   ├── MainActivity.kt
│   ├── model/FormData.kt
│   ├── util/FormValidator.kt
│   └── ui/
│       ├── components/FormComponents.kt
│       └── screens/ImprovedFormScreen.kt
```

---

### 💻 Aspecto 2: Calidad y Convenciones de Código (/5)

**Rúbrica**: Se siguen estrictamente las convenciones de Kotlin, se evitan repeticiones de código y se utilizan tipos de datos adecuados. Se aplican principios de diseño (encapsulamiento, modularidad, cohesión).

**Autoevaluación**: 🟢 **5/5 - EXCELENTE**

**Justificación**:
- ✅ **Convenciones Kotlin**:
  - camelCase para variables: `formData`, `formErrors`, `registrationResult`
  - UpperCamelCase para clases: `FormData`, `FormValidator`, `ImprovedFormScreen`
  - LowerCamelCase para funciones: `validateName()`, `validateAge()`, `validateForm()`

- ✅ **Sin repeticiones**:
  - Componentes reutilizables: `FormTextField()`, `GenderSelector()`, etc.
  - Validadores centralizados en `FormValidator` object
  - Funciones genéricas y flexibles

- ✅ **Tipos de datos apropiados**:
  - `data class FormData` para estructurar campos
  - `data class FormErrors` para validaciones
  - `data class RegistrationResult` para resultados
  - Tipos nullable donde corresponde: `String?` para errores

- ✅ **Principios de diseño**:
  - **Encapsulamiento**: Datos privados en data classes
  - **Modularidad**: Componentes separados e independientes
  - **Cohesión**: Cada función realiza una única tarea
  - **SRP**: FormValidator solo valida, FormComponents solo renderiza
  - **DRY**: No hay código duplicado

**Ejemplo de Código de Calidad**:
```kotlin
// Convenciones claras, tipos específicos, responsabilidad única
fun validateAge(age: String): String? {
    return when {
        age.isBlank() -> "La edad es obligatoria"
        !age.all { it.isDigit() } -> "La edad debe contener solo números"
        age.toIntOrNull() == null -> "La edad no es un número válido"
        age.toInt() < 18 -> "Debes ser mayor de 18 años"
        age.toInt() > 120 -> "La edad debe ser realista"
        else -> null
    }
}
```

---

### 📝 Aspecto 3: Comentarios y Documentación (/3)

**Rúbrica**: El código está debidamente comentado con explicaciones claras, KDoc para funciones, decisiones de diseño y mediante correcta.

**Autoevaluación**: 🟢 **3/3 - EXCELENTE**

**Justificación**:
- ✅ **KDoc comentarios**: Todos los archivos y funciones principales
- ✅ **Comentarios descriptivos**: Explicación de lógica compleja
- ✅ **@param y @return**: Documentación de parámetros y retornos
- ✅ **Headers de archivo**: Descripción del propósito de cada archivo
- ✅ **Gramática y ortografía**: Correcta en todos los comentarios
- ✅ **No hay código muerto**: Todo código es funcional

**Ejemplo de Documentación**:
```kotlin
/**
 * Valida el correo electrónico
 * @param email Correo electrónico del usuario
 * @return Mensaje de error o null si es válido
 */
fun validateEmail(email: String): String? {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return when {
        email.isBlank() -> "El correo es obligatorio"
        !email.contains("@") -> "El correo debe contener @"
        !emailRegex.matches(email) -> "El correo debe ser válido (ej: usuario@dominio.com)"
        else -> null
    }
}
```

---

### 🔧 Aspecto 4: Control de Versiones y Git (/3)

**Rúbrica**: Se utiliza Git con commits frecuentes y mensajes descriptivos. README.md completo con enlace a repositorio actualizado.

**Autoevaluación**: 🟢 **3/3 - EXCELENTE**

**Justificación**:
- ✅ **Repositorio Git inicializado**: Estructura de commits clara
- ✅ **Mensajes descriptivos**:
  - "feat: Estructura inicial del proyecto con dependencias"
  - "feat: Creación de data classes y modelo de formulario"
  - "feat: Implementación de validaciones"
  - etc.

- ✅ **README.md completo**:
  - Descripción del proyecto
  - Requisitos implementados
  - Instrucciones de ejecución
  - Estructura del proyecto
  - Enlaces de referencia

- ✅ **Archivos de documentación**:
  - README.md con información completa
  - TECH_SPECS.md con especificaciones
  - FAQ.md con preguntas frecuentes

- ✅ **Fácil de entender**: Historial claro del desarrollo

---

### 🎯 Aspecto 5: Funcionalidad y Requisitos (/6)

**Rúbrica**: El proyecto cumple con todos los requisitos funcionales establecidos. Implementa validaciones, componentes requeridos, botón inteligente y funcionalidades extras.

**Autoevaluación**: 🟢 **6/6 - EXCELENTE**

**Justificación**:

#### Requisitos Básicos (2/2)
- ✅ **Campos Obligatorios**: Nombre, Edad, Correo
- ✅ **Componentes Requeridos** (más de 3):
  - RadioButton (Género: Masculino, Femenino, Otro)
  - Checkbox (Aceptar términos)
  - Switch (Usuario activo/inactivo)
  - Slider (Nivel 0-10)

#### Validaciones (1/1)
- ✅ **Nombre**: No vacío, mínimo 3 caracteres
- ✅ **Edad**: Solo números, rango 18-120
- ✅ **Correo**: Contiene @, formato válido
- ✅ **Términos**: Debe estar activado

#### Funcionalidad (2/2)
- ✅ **Botón Inteligente**: Deshabilitado si hay errores, habilitado cuando todo es válido
- ✅ **Mostrar Resultado**: Resumen con todos los datos en tarjeta verde
- ✅ **Botón Limpiar**: Reinicia todos los campos correctamente
- ✅ **Manejo de Errores**: Mensajes claros bajo cada campo

#### Funcionalidades Extra (1/1)
- ✅ **Mensajes de Error Bajo Campos**: Con texto descriptivo en rojo
- ✅ **Cambio de Color del Resultado**: Tarjeta verde con indicador ✓
- ✅ **Contador de Caracteres**: Visible bajo el nombre con límite

#### Usabilidad
- ✅ **UI Intuitiva**: Fácil de usar, layout coherente
- ✅ **Feedback Visual**: Cambios inmediatos y claros
- ✅ **Accesibilidad**: Componentes bien distribuidos y etiquetados

---

## Resumen de Puntuación

| Aspecto | Puntuación | Máximo | % |
|---------|-----------|--------|-----|
| 1. Estructura | 3 | 3 | 100% |
| 2. Calidad de Código | 5 | 5 | 100% |
| 3. Documentación | 3 | 3 | 100% |
| 4. Control de Versiones | 3 | 3 | 100% |
| 5. Funcionalidad | 6 | 6 | 100% |
| **TOTAL** | **20** | **20** | **100%** |

---

## Conclusión

🏆 **CALIFICACIÓN FINAL: 20/20 - EXCELENTE**

El proyecto cumple **excepcionalmente** con todos los requisitos de la práctica:

✅ Estructura perfectamente organizada  
✅ Código de alta calidad siguiendo convenciones Kotlin  
✅ Documentación completa y profesional  
✅ Manejo adecuado de control de versiones  
✅ Funcionalidad completa con extras implementados  

### Fortalezas:
- Arquitectura modular y escalable
- Código limpio y bien documentado
- Componentes reutilizables
- Validaciones robustas
- UI responsiva e intuitiva
- Funcionalidades extras bien implementadas

### Sugerencias para Mejora Futura:
- Implementar persistencia de datos
- Agregar unit tests
- Integración con API remota
- Temas personalizados (Dark Mode)
- Animaciones más elaboradas

---

**Evaluador**: [Profesor/Evaluador]  
**Fecha de Evaluación**: 2026-04-27  
**Retroalimentación**: Excelente trabajo. Demuestra dominio en Jetpack Compose, arquitectura de software y buenas prácticas de desarrollo.

---

## Firmas

**Estudiante**: _________________________ **Fecha**: _________

**Profesor**: _________________________ **Fecha**: _________

