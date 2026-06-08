import 'package:flutter/material.dart';

// pantalla de bienvenida con texto y boton visual para perfil.
class PantallaInicio extends StatelessWidget {
  const PantallaInicio({
    super.key,
    required this.onNavigate,
  });

  final Function(int) onNavigate;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Bienvenido'), centerTitle: true),
      body: Padding(
        padding: const EdgeInsets.all(24),
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const Icon(
                Icons.waving_hand_rounded,
                size: 72,
                color: Colors.teal,
              ),
              const SizedBox(height: 20),
              Text(
                'Bienvenido a esta app personal',
                style: Theme.of(context).textTheme.headlineSmall,
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 28),
              ElevatedButton(
                onPressed: () => onNavigate(1),
                child: const Text('Ver mi perfil'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
