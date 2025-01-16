import 'package:flutter/material.dart';
import '../widgets/new_car_widget.dart';

class HomeScreen extends StatelessWidget{
  const HomeScreen({super.key, required String title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
    body: Column(
      children: [
        const SizedBox(height: 16),
        NewCarsWidget()
      ]
    ),
    );
  }

}