import 'package:flutter/material.dart';

// fecha inicio: 2026-06-07
// fecha ultimo cambio: 2026-06-07
// creado por: Alex RP
// descripcion: pantalla de perfil personal con foto, descripcion y contacto.
class PantallaPerfil extends StatelessWidget {
  const PantallaPerfil({
    super.key,
    required this.onNavigate,
  });

  final Function(int) onNavigate;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Perfil personal'), centerTitle: true),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // avatar generico: se puede cambiar despues por una foto real.
              radius: 65,
              backgroundColor: Colors.transparent,
              child: Icon(Icons.person, size: 72, color: Colors.teal),
            ),
            const SizedBox(height: 18),
            Text(
              'Mi perfil personal',
              style: Theme.of(context).textTheme.headlineSmall,
            ),
            const SizedBox(height: 10),
            Text(
              'Estudiante interesado en el desarrollo de apps móviles y en crear interfaces limpias.',
              style: Theme.of(context).textTheme.bodyLarge,
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 24),
            // tarjetas para organizar la informacion de contacto.
            _ContactoItem(
              icono: Icons.email_outlined,
              titulo: 'Correo electrónico',
              dato: 'arhoddop@ulasalle.edu.pe',
            ),
            const SizedBox(height: 10),
            _ContactoItem(
              icono: Icons.phone_outlined,
              titulo: 'Teléfono',
              dato: '+51 931976686',
            ),
            const SizedBox(height: 10),
            _ContactoItem(
              icono: Icons.location_on_outlined,
              titulo: 'Ubicación',
              dato: 'Arequipa, Perú',
            ),
            const SizedBox(height: 24),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () => onNavigate(0),
                  child: const Text('Atrás'),
                ),
                ElevatedButton(
                  onPressed: () => onNavigate(2),
                  child: const Text('Hobbies'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class _ContactoItem extends StatelessWidget {
  const _ContactoItem({
    required this.icono,
    required this.titulo,
    required this.dato,
  });

  final IconData icono;
  final String titulo;
  final String dato;

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: Colors.teal.withValues(alpha: 0.10),
        borderRadius: BorderRadius.circular(14),
      ),
      child: Row(
        children: [
          Icon(icono, color: Colors.teal.shade700),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  titulo,
                  style: const TextStyle(fontWeight: FontWeight.w600),
                ),
                const SizedBox(height: 2),
                Text(dato),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
