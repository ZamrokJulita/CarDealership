import 'package:flutter/material.dart';
import '../widgets/new_car_widget.dart';
import '../widgets/used_cars_widget.dart';
import '../widgets/change_state_widget.dart';
import '../widgets/prorotype_information_car_widget.dart';


class HomeScreen extends StatelessWidget{
  const HomeScreen({super.key, required String title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
    body: Column(
      children: [
        const SizedBox(height: 16),
        NewCarsWidget(),
        UsedCarsWidget(),
        ChangeStateWidget(),
        ProrotypeInformationCarWidget()
      ]
    ),
    );
  }

}