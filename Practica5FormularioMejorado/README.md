# Práctica 5: Formulario Mejorado con Componentes de Compose

## Descripción
Este proyecto implementa un formulario interactivo y mejorado usando **Jetpack Compose**, uno de los frameworks modernos más avanzados para desarrollo de interfaces de usuario en Android.

**Objetivo**: Reforzar el uso de estado, validaciones y componentes de Compose creando un formulario más completo e interactivo.

---

## Requisitos Implementados  ✅

### 1. **Campos Obligatorios** 
- ✅ Nombre (con validación)
- ✅ Edad (solo números)
- ✅ Correo Electrónico (validación de formato)

### 2. **Componentes Requeridos** (+3 obligatorio)
- ✅ **RadioButton**: Seleccionar género (Masculino, Femenino, Otro)
- ✅ **Checkbox**: Aceptar términos y condiciones
- ✅ **Switch**: Usuario activo/inactivo
- ✅ **Slider**: Nivel de experiencia (0-10)

### 3. **Validaciones Implementadas**
- ✅ **Nombre**: No vacío, mínimo 3 caracteres
- ✅ **Edad**: Solo números, entre 18 y 120 años
- ✅ **Correo**: Contiene "@", formato válido (usuario@dominio.com)
- ✅ **Checkbox (Términos)**: Debe estar activado para registrarse

### 4. **Botón Inteligente**
- ✅ El botón "Registrar" está **deshabilitado** si hay errores
- ✅ Se activa solo cuando **todo es válido**

### 5. **Mostrar Resultado**
- ✅ Al presionar el botón, muestra un resumen con:
  - Nombre
  - Edad
  - Correo
  - Género
  - Estado (Activo/Inactivo)
  - Nivel de Experiencia

### 6. **Botón Limpiar**
- ✅ Reinicia todos los campos
- ✅ Limpia todos los componentes (switch, checkbox, etc.)
- ✅ Oculta el resultado

---

## Funcionalidades Extras ⭐

### Implementados:
1. **Mensajes de Error Debajo de Cada Campo**
   - El programa muestra mensajes de error descriptivos en color rojo bajo cada campo que no cumpla la validación
   - Los mensajes desaparecen automáticamente al corregir los errores

2. **Cambio de Color del Resultado**
   - La tarjeta de resultado tiene un fondo verde claro (success)
   - Texto verde oscuro para mejor visibilidad
   - Iconografía visual "✓" para indicar registro exitoso

3. **Contador de Caracteres en el Nombre**
   - Muestra "N/50 caracteres" bajo el campo de nombre
   - Cambia a rojo si se exceda el límite
   - Límite máximo de 50 caracteres implementado

---

## Estructura del Proyecto

```
Practica5FormularioMejorado/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/practica5formulariomejorado/
│   │   │   │   ├── MainActivity.kt                    # Punto de entrada
│   │   │   │   ├── model/
│   │   │   │   │   └── FormData.kt                   # Data classes
│   │   │   │   ├── util/
│   │   │   │   │   └── FormValidator.kt              # Lógica de validación
│   │   │   │   └── ui/
│   │   │   │       ├── components/
│   │   │   │       │   └── FormComponents.kt         # Componentes reutilizables
│   │   │   │       └── screens/
│   │   │   │           └── ImprovedFormScreen.kt     # Pantalla principal
│   │   │   └── res/
│   │   │       └── ...  (recursos)
│   │   └── ...
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml                            # Versiones de dependencias
├── build.gradle.kts
├── settings.gradle.kts
└── README.md                                         # Este archivo
```

---

## Convenciones de Código Aplicadas ✅

### Nomenclatura Kotlin
- ✅ **camelCase** para variables y funciones
- ✅ **UpperCamelCase** para clases (PascalCase)
- ✅ **LowerCamelCase** para métodos
- ✅ **UPPER_SNAKE_CASE** para constantes

### Principios de Diseño
- ✅ **Encapsulamiento**: Datos privados, acceso controlado
- ✅ **Modularidad**: Componentes separados y reutilizables
- ✅ **Cohesión**: Cada archivo tiene una responsabilidad clara
- ✅ **SRP** (Single Responsibility Principle): 
  - `FormValidator` maneja validaciones
  - `FormComponents` maneja componentes UI
  - `ImprovedFormScreen` maneja la lógica de la pantalla

---

## Documentación del Código 📝

### Todos los archivos incluyen:
- ✅ **KDoc comentarios** en funciones principales
- ✅ **Comentarios explicativos** en lógica compleja
- ✅ **Descripción de parámetros** con `@param`
- ✅ **Descripción de retorno** con `@return`
- ✅ **Notas importantes** sobre restricciones o validaciones

### Ejemplo:
```kotlin
/**
 * Valida la edad del usuario
 * @param age Edad del usuario como string
 * @return Mensaje de error o null si es válido
 */
fun validateAge(age: String): String? {
    // ...
}
```

---

## Tecnologías Utilizadas 🛠️

- **Language**: Kotlin 2.0.0
- **Framework**: Jetpack Compose 1.6.0
- **Material Design**: Material3
- **Architecture**: MVVM (Model-View-ViewModel)
- **IDE**: Android Studio (Recommended)
- **Build System**: Gradle con Kotlin DSL

---

## Cómo Ejecutar el Proyecto

### Requisitos Previos:
- Android Studio (versión reciente)
- Android SDK 33 o superior
- Gradle 8.0+

### Pasos:
1. **Clonar el repositorio**:
   ```bash
   git clone <URL-del-repositorio>
   cd Practica5FormularioMejorado
   ```

2. **Abrir en Android Studio**:
   - File → Open → Selecciona la carpeta del proyecto

3. **Compilar y ejecutar**:
   - Click en el botón "Run" o presiona `Shift + F10`
   - Selecciona el emulador o dispositivo físico

4. **Probar el Formulario**:
   - Ingresa datos en los campos
   - Observa los mensajes de error en tiempo real
   - El botón se habilita/deshabilita automáticamente
   - Presiona "Registrar" para ver el resumen
   - Presiona "Limpiar" para resetear el formulario

---

## Validaciones Detalladas

### Nombre
- **Regla**: No vacío, mínimo 3 caracteres, máximo 50
- **Error**: "El nombre es obligatorio" o "El nombre debe tener al menos 3 caracteres"

### Edad
- **Regla**: Solo dígitos, entre 18 y 120 años
- **Errores**: 
  - "La edad es obligatoria"
  - "La edad debe contener solo números"
  - "Debes ser mayor de 18 años"
  - "La edad debe ser realista"

### Correo
- **Regla**: Debe contener "@" y formato válido
- **Regex**: `^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$`
- **Errores**:
  - "El correo es obligatorio"
  - "El correo debe contener @"
  - "El correo debe ser válido (ej: usuario@dominio.com)"

### Términos
- **Regla**: Checkbox debe estar marcado
- **Error**: "Debes aceptar los términos y condiciones"

---

## Historial de Commits

El proyecto tiene commits significativos documentando el progreso:

```
1. Commit: "feat: Estructura inicial del proyecto con dependencias"
2. Commit: "feat: Creación de data classes y modelo de formulario"
3. Commit: "feat: Implementación de validaciones con FormValidator"
4. Commit: "feat: Componentes de formulario (FormComponents)"
5. Commit: "feat: Pantalla principal con ImprovedFormScreen"
6. Commit: "feat: Integración con MainActivity y Jetpack Compose"
```

---

## Enlaces de Referencia

- [Documentación oficial de Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material Design 3 para Compose](https://developer.android.com/jetpack/compose/designsystems/material3)
- [Guía de Estado en Compose](https://developer.android.com/jetpack/compose/state)
- [Best Practices en Android](https://developer.android.com/guide/practices)

---

## Autor
**Estudiante**: [Tu Nombre]  
**Carrera**: Dispositivos Móviles  
**Semestre**: VI  
**Fecha de Creación**: 2026-04-27

---

## Notas Finales

Este proyecto demuestra competencia en:
- ✅ Uso avanzado de Jetpack Compose
- ✅ Gestión de estado en Android
- ✅ Validación de datos
- ✅ Arquitectura limpia y modular
- ✅ Documentación de código profesional
- ✅ Control de versiones con Git

---

**Repositorio GitHub**: [Enlace al repositorio público]

