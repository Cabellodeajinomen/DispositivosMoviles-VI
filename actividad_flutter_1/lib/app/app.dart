import 'package:flutter/material.dart';
import '../pantallas/pantalla_hobbies.dart';
import '../pantallas/pantalla_inicio.dart';
import '../pantallas/pantalla_perfil.dart';

// fecha inicio: 2026-06-07
// fecha ultimo cambio: 2026-06-07
// creado por: Alex RP
// descripcion: configuracion principal de la app con navegacion entre pantallas.
class MiApp extends StatefulWidget {
  const MiApp({super.key});

  @override
  State<MiApp> createState() => _MiAppState();
}

class _MiAppState extends State<MiApp> {
  int _pantallaActual = 0;

  void _cambiarPantalla(int indice) {
    setState(() {
      _pantallaActual = indice;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Perfil y Hobbies',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.teal),
        useMaterial3: true,
      ),
      home: _obtenerPantallaActual(),
    );
  }

  Widget _obtenerPantallaActual() {
    switch (_pantallaActual) {
      case 1:
        return PantallaPerfil(onNavigate: _cambiarPantalla);
      case 2:
        return PantallaHobbies(onNavigate: _cambiarPantalla);
      case 0:
      default:
        return PantallaInicio(onNavigate: _cambiarPantalla);
    }
  }
}
