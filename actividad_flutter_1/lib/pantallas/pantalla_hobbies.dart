import 'package:flutter/material.dart';

// fecha inicio: 2026-06-07
// fecha ultimo cambio: 2026-06-07
// creado por: Alex RP
// descripcion: pantalla con hobbies personales usando columnas, iconos y tarjetas.
class PantallaHobbies extends StatelessWidget {
  const PantallaHobbies({
    super.key,
    required this.onNavigate,
  });

  final Function(int) onNavigate;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Hobbies e intereses'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text(
              'Estas son algunas actividades e intereses que me gustan',
              style: Theme.of(context).textTheme.titleMedium,
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 18),
            _HobbyCard(
              icono: Icons.menu_book_outlined,
              titulo: 'Leer tecnología',
              descripcion: 'Me gusta leer sobre nuevas tecnologias y las ultimas novedades en desarrollo de software.',
            ),
            const SizedBox(height: 12),
            _HobbyCard(
              icono: Icons.sports_esports_outlined,
              titulo: 'Videojuegos',
              descripcion: 'Juego para relajarme y mejorar reflejos; es una actividad que me divierte.',
            ),
            const SizedBox(height: 12),
            _HobbyCard(
              icono: Icons.headphones_outlined,
              titulo: 'Música',
              descripcion: 'Amo la musica y suele acompañarme mientras estudio y trabajo.',
            ),
            const SizedBox(height: 24),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () => onNavigate(0),
                  child: const Text('Inicio'),
                ),
                ElevatedButton(
                  onPressed: () => onNavigate(1),
                  child: const Text('Perfil'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class _HobbyCard extends StatelessWidget {
  const _HobbyCard({
    required this.icono,
    required this.titulo,
    required this.descripcion,
  });

  final IconData icono;
  final String titulo;
  final String descripcion;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: Colors.amber.withValues(alpha: 0.18),
        borderRadius: BorderRadius.circular(14),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Icon(icono, size: 28, color: Colors.orange.shade800),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  titulo,
                  style: const TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w700,
                  ),
                ),
                const SizedBox(height: 4),
                Text(descripcion),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
