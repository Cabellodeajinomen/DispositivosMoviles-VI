import 'package:flutter_test/flutter_test.dart';

import 'package:actividad_flutter_1/app/app.dart';

void main() {
  testWidgets('muestra la pantalla de inicio', (WidgetTester tester) async {
    await tester.pumpWidget(const MiApp());

    expect(find.text('Bienvenido'), findsOneWidget);
    expect(find.text('Ver mi perfil'), findsOneWidget);
  });
}
