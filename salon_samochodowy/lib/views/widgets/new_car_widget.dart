import 'package:flutter/material.dart';
import 'package:iconify_flutter/iconify_flutter.dart';
import 'package:iconify_flutter/icons/ph.dart';
import 'package:salon_samochodowy/views/screens/new_cars_screen.dart';

class NewCarsWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(),
      child: Center(
        child: Padding(
          padding: const EdgeInsets.fromLTRB(10.0, 10.0, 10.0, 30.0),
          child: Column(
              children: [
            Container(
              child: Iconify(
                Ph.car_simple_bold,
                size: 80,
                color: Colors.blue,
              ),
            ),
            Text(
              'Lista samochodów',
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
                    Navigator.push(context, MaterialPageRoute(builder: (context) => const NewCarsScreen(title: 'New Cars')));
                  },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Color(0xD9D9D9),

                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(65), // Set the radius here
                    ),
                        overlayColor: Color(0xFF00A8E8)
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
