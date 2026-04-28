# Guía de Desarrollo - Práctica 5 Formulario Mejorado

## Configuración del Entorno

### Requisitos Previos
- Android Studio **Arctic Fox** o superior
- SDK Android 33 (mínimo)
- Gradle 8.0+
- Kotlin 2.0.0

### Setup Inicial

```bash
# 1. Clonar el repositorio
git clone <URL-repositorio>

# 2. Abrir en Android Studio
cd Practica5FormularioMejorado

# 3. Sincronizar Gradle
./gradlew clean sync

# 4. Compilar el proyecto
./gradlew build

# 5. Ejecutar en emulador o dispositivo
./gradlew installDebug
```

---

## Estructura del Código

### Flujo de Datos

```
MainActivity
    ↓
MaterialTheme + Surface
    ↓
ImprovedFormScreen (State Management)
    ├── formData (FormData)
    ├── formErrors (FormErrors)
    └── registrationResult (RegistrationResult)
        ↓
    FormComponents
    ├── FormTextField (name, age, email)
    ├── GenderSelector (RadioButton)
    ├── ActiveUserSwitch
    ├── LevelSlider
    ├── TermsCheckbox
    └── CharacterCounter
        ↓
    FormValidator (Validations)
```

### Jerarquía de Componentes

1. **MainActivity** (Activity)
   - Punto de entrada de la aplicación
   - Configura MaterialTheme
   - Renderiza ImprovedFormScreen

2. **ImprovedFormScreen** (Composable)
   - Gestión principal de estado
   - Lógica de negocio (validación, registro, limpieza)
   - Layout del formulario

3. **FormComponents** (Composables)
   - `FormTextField`: TextInput reutilizable
   - `GenderSelector`: RadioButton group
   - `ActiveUserSwitch`: Switch component
   - `LevelSlider`: Slider component
   - `TermsCheckbox`: Checkbox with validation
   - `CharacterCounter`: Text counter

4. **FormValidator** (Object)
   - Contiene lógica de validación
   - Funciones puras y stateless

---

## Cómo Agregar Nuevos Campos

### Paso 1: Extender FormData

```kotlin
// En model/FormData.kt
data class FormData(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val gender: String = "Otro",
    val isActive: Boolean = false,
    val level: Float = 0f,
    val acceptTerms: Boolean = false,
    // NUEVO CAMPO
    val phone: String = ""  // ← Agregar aquí
)
```

### Paso 2: Agregar Validación

```kotlin
// En util/FormValidator.kt
fun validatePhone(phone: String): String? {
    return when {
        phone.isBlank() -> "El teléfono es obligatorio"
        !phone.all { it.isDigit() } -> "Solo se permiten números"
        phone.length != 10 -> "El teléfono debe tener 10 dígitos"
        else -> null
    }
}

// Actualizar validateForm()
fun validateForm(formData: FormData): FormErrors {
    return FormErrors(
        nameError = validateName(formData.name),
        ageError = validateAge(formData.age),
        emailError = validateEmail(formData.email),
        termsError = validateTerms(formData.acceptTerms),
        // NUEVO
        phoneError = validatePhone(formData.phone)  // ← Agregar
    )
}
```

### Paso 3: Crear Componente

```kotlin
// En ui/components/FormComponents.kt
@Composable
fun PhoneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    modifier: Modifier = Modifier
) {
    FormTextField(
        value = value,
        onValueChange = { 
            if (it.length <= 15) onValueChange(it) 
        },
        label = "Teléfono",
        error = error,
        keyboardType = KeyboardType.Phone,
        modifier = modifier
    )
}
```

### Paso 4: Agregar a la Pantalla

```kotlin
// En ui/screens/ImprovedFormScreen.kt
FormTextField(
    value = formData.phone,
    onValueChange = { updateFormData(formData.copy(phone = it)) },
    label = "Teléfono",
    error = formErrors.phoneError,
    keyboardType = KeyboardType.Phone,
    modifier = Modifier.padding(bottom = 16.dp)
)
```

---

## Cómo Modificar Validaciones

### Cambiar Regla de Nombre

```kotlin
// Antes
fun validateName(name: String): String? {
    return when {
        name.isBlank() -> "El nombre es obligatorio"
        name.length < 3 -> "El nombre debe tener al menos 3 caracteres"
        else -> null
    }
}

// Después (solo mayúsculas)
fun validateName(name: String): String? {
    return when {
        name.isBlank() -> "El nombre es obligatorio"
        name.length < 3 -> "El nombre debe tener al menos 3 caracteres"
        name != name.uppercase() -> "El nombre debe estar en mayúsculas"  // ← Nueva regla
        else -> null
    }
}
```

---

## Agregar Persistencia de Datos

### Opción 1: SharedPreferences

```kotlin
// Nuevo archivo: util/PreferencesManager.kt
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("formdata", Context.MODE_PRIVATE)
    
    fun saveFormData(formData: FormData) {
        prefs.edit().apply {
            putString("name", formData.name)
            putString("age", formData.age)
            putString("email", formData.email)
            apply()
        }
    }
    
    fun loadFormData(): FormData? {
        val name = prefs.getString("name", null)
        val age = prefs.getString("age", null)
        val email = prefs.getString("email", null)
        
        return if (name != null) {
            FormData(
                name = name,
                age = age ?: "",
                email = email ?: ""
            )
        } else null
    }
}
```

### Opción 2: Room Database

```kotlin
// Nuevo archivo: data/FormDatabase.kt
import androidx.room.*

@Entity(tableName = "forms")
data class FormEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val age: String,
    val email: String,
    val gender: String,
    val isActive: Boolean,
    val level: Float,
    val acceptTerms: Boolean,
    val registrationDate: Long
)

@Dao
interface FormDao {
    @Insert
    suspend fun insertForm(form: FormEntity)
    
    @Query("SELECT * FROM forms ORDER BY registrationDate DESC")
    suspend fun getAllForms(): List<FormEntity>
}
```

---

## Testing

### Unit Testing para Validaciones

```kotlin
// File: app/src/test/java//.../FormValidatorTest.kt
import org.junit.Test
import org.junit.Assert.*
import com.example.practica5formulariomejorado.util.FormValidator

class FormValidatorTest {
    
    @Test
    fun testValidateName_EmptyName() {
        val result = FormValidator.validateName("")
        assertEquals("El nombre es obligatorio", result)
    }
    
    @Test
    fun testValidateName_ShortName() {
        val result = FormValidator.validateName("ab")
        assertEquals("El nombre debe tener al menos 3 caracteres", result)
    }
    
    @Test
    fun testValidateName_ValidName() {
        val result = FormValidator.validateName("Juan")
        assertNull(result)
    }
    
    @Test
    fun testValidateEmail_InvalidEmail() {
        val result = FormValidator.validateEmail("invalid")
        assertNotNull(result)
    }
    
    @Test
    fun testValidateEmail_ValidEmail() {
        val result = FormValidator.validateEmail("usuario@ejemplo.com")
        assertNull(result)
    }
    
    @Test
    fun testValidateAge_UnderMinimum() {
        val result = FormValidator.validateAge("17")
        assertNotNull(result)
    }
    
    @Test
    fun testValidateAge_ValidAge() {
        val result = FormValidator.validateAge("25")
        assertNull(result)
    }
}
```

### Ejecución de Tests

```bash
# Ejecutar todos los tests
./gradlew test

# Ejecutar tests específicos
./gradlew test --tests "FormValidatorTest"

# Con cobertura
./gradlew testDebugUnitTestCoverage
```

---

## Debugging

### Logs

```kotlin
// Agregar logs en FormValidator
import android.util.Log

private const val TAG = "FormValidator"

fun validateName(name: String): String? {
    Log.d(TAG, "Validating name: $name")
    val error = when {
        name.isBlank() -> "El nombre es obligatorio"
        else -> null
    }
    Log.d(TAG, "Name validation result: $error")
    return error
}
```

### Breakpoints

1. Click en el número de línea
2. Ejecutar con debug button
3. Inspeccionar variables

### Profiler

Android Studio → Profiler → Seleccionar app
- Memory Profiler: Uso de memoria
- CPU Profiler: Uso de CPU
- Network Profiler: Tráfico de red

---

## Optimizaciones

### Recomposiciones

```kotlin
// ❌ Malo - Recompone innecesariamente
@Composable
fun BadExample() {
    val data = FormData()  // Nueva instancia cada vez
    FormScreen(data)
}

// ✅ Bueno - Solo recompone cuando cambia
@Composable
fun GoodExample() {
    val data by remember { mutableStateOf(FormData()) }
    FormScreen(data)
}
```

### Rendimiento

```kotlin
// Usar LazyColumn para listas grandes
LazyColumn {
    items(items.size) { index ->
        ItemRow(items[index])
    }
}

// Usar key para identidad estable
LazyColumn {
    items(items, key = { it.id }) { item ->
        ItemRow(item)
    }
}
```

---

## Deploy a Google Play Store

### Pasos Básicos

1. **Crear firma de app**
   ```bash
   keytool -genkey -v -keystore release.jks \
     -keyalg RSA -keysize 2048 -validity 10000
   ```

2. **Configurar en build.gradle.kts**
   ```kotlin
   signingConfigs {
       create("release") {
           storeFile = file("release.jks")
           storePassword = System.getenv("KEYSTORE_PASSWORD")
           keyAlias = "key0"
           keyPassword = System.getenv("KEY_PASSWORD")
       }
   }
   ```

3. **Build APK/AAB**
   ```bash
   ./gradlew bundleRelease
   ```

4. **Upload a Play Store Console**
   - Crear aplicación
   - Subir AAB
   - Completer formularios
   - Publicar

---

## Recursos y Referencias

### Documentación Oficial
- [Jetpack Compose Docs](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Kotlin Docs](https://kotlinlang.org/docs/)

### Tutoriales Recomendados
- Codelab de Compose
- Android Development Course
- Kotlin for Android Developers

### Comunidades
- StackOverflow
- Reddit r/androiddev
- Google Play Community

---

## Troubleshooting

### Problema: "Unresolved reference"
**Solución**: 
```bash
./gradlew clean
./gradlew sync
```

### Problema: "Build failed"
**Solución**:
- Verificar versión de Gradle
- Actualizar SDK
- Limpiar cache: `./gradlew clean`

### Problema: "App crashes"
**Solución**:
- Ver logcat
- Usar debugger
- Verificar nullability

---

## Contribución

Para contribuir al proyecto:

1. Fork el repositorio
2. Crear rama: `git checkout -b feature/nueva-caracteristica`
3. Commit cambios: `git commit -am 'Add feature'`
4. Push: `git push origin feature/nueva-caracteristica`
5. Pull Request

---

## Licencia

Este proyecto está bajo licencia MIT.

---

**Última actualización**: 2026-04-27  
**Autor**: [Tu Nombre]  
**Versión**: 1.0.0

