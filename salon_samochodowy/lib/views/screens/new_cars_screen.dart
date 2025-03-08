import 'package:flutter/material.dart';
import '../widgets/body_type_dropdown.dart';
import '../widgets/drive_type_dropdown.dart';
import '../widgets/engine_power_slider.dart';
import '../widgets/footer_widget.dart';
import '../widgets/fuel_type_dropdown.dart';
import '../widgets/gearbox_type_dropdown.dart';
import '../widgets/header_widget.dart';
import '../widgets/price_slider.dart';
import 'information_about_a_car_screen.dart';

class NewCarsScreen extends StatefulWidget {
  const NewCarsScreen({super.key, required String title});

  @override
  _NewCarsScreenState createState() => _NewCarsScreenState();
}

class _NewCarsScreenState extends State<NewCarsScreen> {
  String? selectedModel;
  String? selectedBodyType;
  String? selectedDriveType;
  String? selectedFuelType;
  String? selectedGearbox;
  double enginePower = 0;
  double price = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: const Size.fromHeight(80),
        child: HeaderWidget(),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 16),

            ExpansionTile(
              title: Text(
                'Filtry',
                style: TextStyle(
                  fontSize: 30,
                  fontWeight: FontWeight.bold,
                  color: Colors.black,
                ),
              ),
              children: [
                const SizedBox(height: 16),
                Row(
                  children: [
                    // Model
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Model',
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                              color: Colors.black,
                            ),
                          ),
                          DropdownButton<String>(
                            isExpanded: true,
                            value: selectedModel,
                            hint: Text("Wybierz model",
                                style: TextStyle(
                                    color: Colors.grey
                                ),
                            ),
                            onChanged: (String? newValue) {
                              setState(() {
                                selectedModel = newValue;
                              });
                            },
                            items: <String>['M3', 'M4', 'M5', 'M6']
                                .map<DropdownMenuItem<String>>((String value) {
                              return DropdownMenuItem<String>(
                                value: value,
                                child: Text(value),
                              );
                            }).toList(),
                          ),
                        ],
                      ),
                    ),
                    SizedBox(width: 16),
                    // Rodzaj nadwozia
                    Expanded(
                      child: BodyTypeDropdown(
                        selectedBodyType: selectedBodyType,
                        onChanged: (String? newValue) {
                          setState(() {
                            selectedBodyType = newValue;
                          });
                        },
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 16),
                Row(
                  children: [
                    Expanded(
                      child: DriveTypeDropdown(
                        selectedDriveType: selectedDriveType,
                        onChanged: (String? newValue) {
                          setState(() {
                            selectedDriveType = newValue;
                          });
                        },
                      ),
                    ),
                    SizedBox(width: 16),
                    Expanded(
                      child: FuelTypeDropdown(
                        selectedFuelType: selectedFuelType,
                        onChanged: (String? newValue) {
                          setState(() {
                            selectedFuelType = newValue;
                          });
                        },
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 16),
                Row(
                  children: [
                    Expanded(
                      child: GearboxTypeDropdown(
                        selectedGearbox: selectedGearbox,
                        onChanged: (String? newValue) {
                          setState(() {
                            selectedFuelType = newValue;
                          });
                        },
                      ),
                    ),
                    SizedBox(width: 16),
                    Expanded(
                      child: EnginePowerSlider(
                        enginePower: enginePower,
                        onChanged: (double value) {
                          setState(() {
                            enginePower = value;
                          });
                        },
                      ),

                    ),
                  ],
                ),
                const SizedBox(height: 16),
                PriceSlider(
                  price: price,
                  onChanged: (double value) {
                    setState(() {
                      price = value;
                    });
                  },
                ),
              ],
            ),
            const SizedBox(height: 16),
            Center(
              child: ElevatedButton(
                onPressed: () {
                },
                style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.grey,
                ),
                child: Text(
                  'Wyszukaj',
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.black,
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top:20.0),
              child: Center(
                child:GestureDetector(
                  onTap: (){
                    Navigator.push(context,MaterialPageRoute(builder: (context)=>InformationAboutACarScreen(title: 'Information')),);
                  },
                child: Container(
                  width:350,
                  height:100,
                  decoration: BoxDecoration(
                    color: Color(0xFFD9D9D9),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(left:10.0,right:100.0),
                        child: ClipRRect(
                          borderRadius: BorderRadius.circular(20),
                        child:Image.asset('assets/auto1.png',
                            height:70,
                          fit: BoxFit.cover,),
                      ),
                      ),
                         Padding(
                          padding: const EdgeInsets.only(left:5.0),
                          child: Text(
                            'BMW iX3',
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                              color: Colors.black,
                            ),
                          ),
                        ),
                    ],
                  ),
                ),
              ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top:20.0),
              child: Center(
                child: Container(
                  width:350,
                  height:100,
                  decoration: BoxDecoration(
                    color: Color(0xFFD9D9D9),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(left:10.0,right:100.0),
                        child: ClipRRect(
                          borderRadius: BorderRadius.circular(20),
                          child:Image.asset('assets/auto2.png',
                            height:70,
                            fit: BoxFit.cover,),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left:5.0),
                        child: Text(
                          'BMW X1',
                          style: TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                            color: Colors.black,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top:20.0),
              child: Center(
                child: Container(
                  width:350,
                  height:100,
                  decoration: BoxDecoration(
                    color: Color(0xFFD9D9D9),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(left:10.0,right:100.0),
                        child: ClipRRect(
                          borderRadius: BorderRadius.circular(20),
                          child:Image.asset('assets/auto3.png',
                            height:70,
                            fit: BoxFit.cover,),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left:5.0),
                        child: Text(
                          'BMW X6',
                          style: TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                            color: Colors.black,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: FooterWidget(),
    );
  }
}
