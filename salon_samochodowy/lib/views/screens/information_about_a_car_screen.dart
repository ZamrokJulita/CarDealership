import 'package:flutter/material.dart';
import '../widgets/information_about_a_car_widget.dart';
import '../widgets/header_widget.dart';
import '../widgets/footer_widget.dart';

class InformationAboutACarScreen extends StatelessWidget{
  const InformationAboutACarScreen({super.key, required String title});


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: const Size.fromHeight(80),
        child: HeaderWidget(),
      ),
      body: Column(
          children: [
            const SizedBox(height: 16),
            InformationAboutACarWidget(),
          ]
      ),
      bottomNavigationBar: FooterWidget(),
    );
  }

}