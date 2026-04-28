#!/usr/bin/env python3
"""
Script de validación del proyecto Práctica 5
Verifica que todos los archivos necesarios estén presentes
"""

import os
import sys
from pathlib import Path

class ProjectValidator:
    def __init__(self, project_root):
        self.project_root = Path(project_root)
        self.results = {
            'successful': [],
            'failed': []
        }

    def check_file(self, file_path, description):
        """Verifica si un archivo existe"""
        full_path = self.project_root / file_path
        exists = full_path.exists()

        status = "✅" if exists else "❌"
        message = f"{status} {description}: {file_path}"

        if exists:
            self.results['successful'].append(message)
        else:
            self.results['failed'].append(message)

        print(message)

    def check_folder(self, folder_path, description):
        """Verifica si una carpeta existe"""
        full_path = self.project_root / folder_path
        exists = full_path.exists() and full_path.is_dir()

        status = "✅" if exists else "❌"
        message = f"{status} Carpeta {description}: {folder_path}"

        if exists:
            self.results['successful'].append(message)
        else:
            self.results['failed'].append(message)

        print(message)

    def validate(self):
        """Ejecuta todas las validaciones"""
        print("\n" + "="*60)
        print("VALIDACIÓN DE PROYECTO - PRÁCTICA 5")
        print("="*60 + "\n")

        # Verificar carpetas
        print("📁 VERIFICANDO CARPETAS...")
        self.check_folder(
            "app/src/main/java/com/example/practica5formulariomejorado/model",
            "Data Models"
        )
        self.check_folder(
            "app/src/main/java/com/example/practica5formulariomejorado/util",
            "Utilities"
        )
        self.check_folder(
            "app/src/main/java/com/example/practica5formulariomejorado/ui/components",
            "UI Components"
        )
        self.check_folder(
            "app/src/main/java/com/example/practica5formulariomejorado/ui/screens",
            "UI Screens"
        )

        # Verificar archivos de código
        print("\n💻 VERIFICANDO ARCHIVOS DE CÓDIGO...")
        self.check_file(
            "app/src/main/java/com/example/practica5formulariomejorado/MainActivity.kt",
            "MainActivity"
        )
        self.check_file(
            "app/src/main/java/com/example/practica5formulariomejorado/model/FormData.kt",
            "FormData Classes"
        )
        self.check_file(
            "app/src/main/java/com/example/practica5formulariomejorado/util/FormValidator.kt",
            "Form Validator"
        )
        self.check_file(
            "app/src/main/java/com/example/practica5formulariomejorado/ui/components/FormComponents.kt",
            "Form Components"
        )
        self.check_file(
            "app/src/main/java/com/example/practica5formulariomejorado/ui/screens/ImprovedFormScreen.kt",
            "Improved Form Screen"
        )

        # Verificar configuración
        print("\n⚙️  VERIFICANDO CONFIGURACIÓN...")
        self.check_file("build.gradle.kts", "Build gradle (Project)")
        self.check_file("app/build.gradle.kts", "Build gradle (App)")
        self.check_file("gradle/libs.versions.toml", "Gradle libs versions")
        self.check_file("settings.gradle.kts", "Settings gradle")
        self.check_file(".gitignore", "Git ignore")

        # Verificar documentación
        print("\n📚 VERIFICANDO DOCUMENTACIÓN...")
        self.check_file("README.md", "README principal")
        self.check_file("TECH_SPECS.md", "Especificaciones técnicas")
        self.check_file("FAQ.md", "Preguntas frecuentes")
        self.check_file("RUBRICA.md", "Rúbrica de evaluación")
        self.check_file("CHANGELOG.md", "Changelog")
        self.check_file("DEVELOPMENT_GUIDE.md", "Guía de desarrollo")
        self.check_file("INSTRUCCIONES_ENTREGA.md", "Instrucciones de entrega")

        # Resumen
        print("\n" + "="*60)
        print("RESUMEN DE VALIDACIÓN")
        print("="*60)
        print(f"✅ Archivos exitosos: {len(self.results['successful'])}")
        print(f"❌ Archivos faltantes: {len(self.results['failed'])}")

        if self.results['failed']:
            print("\nArchivos faltantes:")
            for msg in self.results['failed']:
                print(f"  {msg}")

        print("\n" + "="*60)

        return len(self.results['failed']) == 0

if __name__ == "__main__":
    # Obtener ruta del proyecto
    project_root = os.path.dirname(os.path.abspath(__file__))

    # Validar
    validator = ProjectValidator(project_root)
    success = validator.validate()

    # Salida
    sys.exit(0 if success else 1)

