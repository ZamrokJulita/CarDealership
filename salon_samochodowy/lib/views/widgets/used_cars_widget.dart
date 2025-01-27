import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:iconify_flutter/iconify_flutter.dart';
import 'package:iconify_flutter/icons/ph.dart';
import 'package:iconify_flutter/icons/mdi.dart';
import 'package:salon_samochodowy/views/screens/used_cars_screen.dart';

class UsedCarsWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(),
      child: Center(
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                      Transform.translate(
                        offset: Offset(30, 0),
                      child: Iconify(
                        Ph.car_simple_bold,
                        size: 80,
                        color: Colors.blue,
                      ),
                    ),
                        Transform.translate(
                          offset: Offset(6, 30),
                      child:Transform.rotate(
                        angle: 3.14159/2,
                        child: Iconify(
                          Mdi.magnify,
                          size: 55,
                          color: Colors.blue,
                        ),
                      ),
                    ),
                  ],
                ),
                Text(
                  'Używane samochody',
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: Colors.black,
                    fontFamily: 'Inter',
                    fontSize: 26,
                    fontWeight: FontWeight.bold,

                  ),
                ),
                SizedBox(
                  width: 150,
                  height: 45,
                  child: ElevatedButton(
                      onPressed: (){
                        Navigator.push(context, MaterialPageRoute(builder: (context) => const UsedCarsScreen(title: 'Used cars')));
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Color(0xD9D9D9),

                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(65), // Set the radius here
                        ),
                      ),
                      child: Text(
                        'Wyszukaj',
                        style: TextStyle(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: 18,
                        ),
                      )),
                )
              ]),
        ),
      ),
    );
  }
}
