package com.example.interaccionconimagenes

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Prueba instrumentada que corre en un dispositivo Android.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Contexto de la app en prueba.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.interaccionconimagenes", appContext.packageName)
    }
}