import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:iconify_flutter/iconify_flutter.dart';
import 'package:iconify_flutter/icons/ph.dart';
import 'package:iconify_flutter/icons/mdi.dart';
import 'package:salon_samochodowy/views/screens/information_about_a_car_screen.dart';

class ProrotypeInformationCarWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(),
      child: Center(
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
              children: [
                SizedBox(
                  width: 150,
                  height: 20,
                  child: ElevatedButton(
                      onPressed: (){
                        Navigator.push(context, MaterialPageRoute(builder: (context) => const InformationAboutACarScreen(title: 'New Cars')));
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Color(0xD9D9D9),

                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(65), // Set the radius here
                        ),
                      ),
                      child: Text(
                        'Informacje',
                        style: TextStyle(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: 15,
                        ),
                      )),
                )
              ]),
        ),
      ),
    );
  }
}
