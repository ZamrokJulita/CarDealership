import 'package:flutter/material.dart';
import '../widgets/details_about_a_car_widget.dart';


class InformationAboutACarScreen extends StatelessWidget{
  const InformationAboutACarScreen({super.key, required String title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
          children: [
            const SizedBox(height: 16),
            DetailsAboutACarWidget()
          ]
      ),
    );
  }

}