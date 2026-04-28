# Preguntas Frecuentes (FAQ) - Práctica 5 Formulario Mejorado

## ¿Cómo funciona la validación?

La validación se realiza en tiempo real mientras el usuario escribe:
- Cada cambio en un campo dispara la revalidación del formulario
- Los errores se muestran en rojo debajo del campo afectado
- El botón de Registrar se deshabilita automáticamente si hay errores

## ¿Por qué el botón está deshabilitado?

El botón "Registrar" se deshabilita cuando hay al menos un error de validación:
- Nombre sin llenar o menos de 3 caracteres
- Edad vacía, no numérica o fuera de rango
- Correo inválido o sin @
- Checkbox de términos no marcado

## ¿Qué sucede al presionar "Limpiar"?

El botón limpiar:
1. Borra el nombre completamente
2. Borra la edad
3. Borra el correo
4. Reinicia el género a "Otro"
5. Coloca el switch en "No activo"
6. Resetea el slider a nivel 0
7. Desmarca el checkbox de términos
8. Oculta el resultado previo

## ¿Cuál es el rango válido para edad?

- Mínimo: 18 años
- Máximo: 120 años
- Solo números enteros permitidos
- Sin decimales ni caracteres especiales

## ¿Se puede editar después de registrar?

Sí, puedes:
- Cambiar cualquier campo después de registrar
- El resultado se actualiza cuando presionales "Registrar" nuevamente
- Presiona "Limpiar" para empezar de cero

## ¿Límites de caracteres?

- **Nombre**: Máximo 50 caracteres (contador visible)
- **Edad**: 2-3 dígitos máximo
- **Correo**: Sin límite específico de caracteres

## ¿Cómo veo la validación del correo?

El correo se valida usando una expresión regular que verifica:
- Presencia de dirección local (usuario)
- Símbolo @ obligatorio
- Dominio válido
- Extensión de al menos 2 caracteres

**Ejemplo válido**: usuario@ejemplo.com
**Ejemplo inválido**: usuario@.com o usuarioejemplo.com

## ¿El slider es obligatorio?

No, el slider es opcional:
- Comienza en 0
- Se puede dejar en 0 sin problemas
- Solo se valida su rango (0-10)

## ¿Debo aceptar los términos?

Sí, el checkbox de términos es obligatorio:
- Debes marcarlo para poder registrarte
- Sin él, el botón permanecerá deshabilitado
- Puedes desmarcarlo después de registrar

## ¿Qué pasa con los datos después de registrar?

Los datos se muestran solo en pantalla:
- Se guardan en el estado de la app
- Si cierras la app, se pierden (sin persistencia en BD)
- Los datos se mantienen mientras no presiones "Limpiar"

---

**Última actualización**: 2026-04-27

