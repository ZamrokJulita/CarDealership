import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:iconify_flutter/iconify_flutter.dart';
import 'package:iconify_flutter/icons/ph.dart';
import 'package:iconify_flutter/icons/mdi.dart';
import 'package:salon_samochodowy/views/screens/information_about_a_car_screen.dart';
import 'package:salon_samochodowy/views/screens/list_of_clients_and_sales_screen.dart';
import '../screens/client_form_screen.dart';


class InformationAboutACarWidget extends StatefulWidget {
  @override
  _InformationAboutACarWidgetState createState() => _InformationAboutACarWidgetState();
}

class _InformationAboutACarWidgetState extends State<InformationAboutACarWidget> {
  bool _isEditing = false;

  TextEditingController _priceController = TextEditingController(text: '247 955 PLN');
  TextEditingController _colorController = TextEditingController(text: 'Storm Bay metalizowany');
  TextEditingController _modelController = TextEditingController(text: 'BMW iX3');
  TextEditingController _bodyTypeController = TextEditingController(text: 'SUV');
  TextEditingController _driveTypeController = TextEditingController(text: 'na tylne koła');
  TextEditingController _fuelTypeController = TextEditingController(text: 'samochód elektryczny');
  TextEditingController _transmissionController = TextEditingController(text: 'jednobiegowa przekładnia');
  TextEditingController _powerController = TextEditingController(text: '286 KM');
  TextEditingController _descriptionController = TextEditingController(text: 'BMW iX3 otwiera nową erę radości z jazdy bez emisji spalin. Odkryj pierwsze w pełni elektryczne BMW Sport Activity Vehicle, które łączy w sobie to, co najlepsze z dwóch światów: dynamikę jazdy i najwyższą jakość BMW X3 ze zwiększonymi osiągami i wydajnością technologii BMW eDrive piątej generacji. Dzięki zasięgowi do 460 km* i zużyciu energii od 18,5 kWh/100 km* BMW iX3 wyznacza nowe standardy. A inteligentne produkty i dostosowanym do potrzeb usługi BMW Charging sprawiają, że ładowanie jest łatwiejsze, szybsze i wydajniejsze niż kiedykolwiek dotąd. W pełni dopracowane, w pełni elektryczne: BMW iX3 już dzisiaj demonstruje elektryzującą przyszłość.');

  @override
  Widget build(BuildContext context) {

    return Center(
      child: Container(
        padding: EdgeInsets.only(top: 0.0),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Padding(
                  padding: const EdgeInsets.only(right:15.0),
                  child: Text(
                    'BMW iX3',
                    style: TextStyle(
                      fontSize: 20.0,
                      fontWeight: FontWeight.bold,
                      color: Colors.blue,
                    ),
                    textAlign: TextAlign.center,
                  ),
                ),
                GestureDetector(
                  onTap: () {
                    setState(() {
                      _isEditing = !_isEditing;
                    });
                  },
                  child: Iconify(
                    _isEditing ? Mdi.check : Mdi.pencil,
                    size: 20,
                    color: Colors.blue,
                  ),
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.only(top: 20.0,bottom: 20.0,left:2.0),
              child: Row(
                children: [
                  Align(
                    alignment: Alignment.centerLeft,


                    child:ClipRRect(
                      borderRadius: BorderRadius.circular(20.0),
                      child: Image.asset(
                        'assets/auto1.png',
                        height: 170.0,
                        width: 230.0,
                        fit: BoxFit.cover,
                      ),
                    ),

                  ),
                  SizedBox(width: 16.0),
                  Container(
                    alignment: Alignment.center,
                    height: 280,
                    width: 150,
                    child: Column(
                      children: [
                        Expanded(
                          child: SingleChildScrollView(
                            scrollDirection: Axis.vertical,
                            child: _isEditing
                                ? TextField(
                              controller: _descriptionController,
                              style: TextStyle(fontSize: 12.0, color: Colors.black),
                              maxLines: null,
                              decoration: InputDecoration(
                                border: OutlineInputBorder(),
                                contentPadding: EdgeInsets.all(8.0),
                              ),
                            )
                                : Text(
                              _descriptionController.text,
                              style: TextStyle(fontSize: 12.0, color: Colors.black),
                              textAlign: TextAlign.justify,
                              overflow: TextOverflow.visible,
                            ),
                          ),
                        ),

                      ],
                    ),
                  ),
                ],
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Padding(
                  padding: const EdgeInsets.only(right:8.0),
                  child: Text(
                      'PARAMETRY',
                      style:TextStyle(
                          fontSize: 20.0,
                          color:Colors.black,
                          fontWeight: FontWeight.bold
                      )
                  ),
                ),
                Iconify(
                  Mdi.wrench,
                  size: 20,
                  color: Colors.black,
                ),
              ],
            ),
            Container(
                alignment: Alignment.center,

                height:300,
                width:350,
                child:Column(
                    children:[
                      Expanded(child:
                      ListView(
                          scrollDirection:Axis.vertical ,
                          children:[
                            Padding(
                              padding: const EdgeInsets.only(top:0.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Cena',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _priceController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _priceController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Kolor lakieru',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _colorController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _colorController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Model',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _modelController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _modelController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Rodzaj nadwozia',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _bodyTypeController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _bodyTypeController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Rodzaj napędu',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _driveTypeController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _driveTypeController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Rodzaj paliwa',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _fuelTypeController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _fuelTypeController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Skrzynia biegów',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _transmissionController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _transmissionController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                            Padding(
                              padding: const EdgeInsets.only(top:20.0),
                              child: Container(
                                  width:350,
                                  height:50,
                                  decoration: BoxDecoration(
                                    color: Colors.grey,
                                    borderRadius: BorderRadius.circular(15.0),
                                  ),
                                  child:Row(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children:[
                                        Padding(
                                          padding: const EdgeInsets.only(left:15.0),
                                          child: Text(
                                              'Moc silnika',
                                              style:TextStyle(
                                                  fontSize:15.0,
                                                  fontWeight: FontWeight.bold,
                                                  color:Colors.black
                                              )
                                          ),
                                        ),
                                        Padding(
                                          padding: const EdgeInsets.only(right: 15.0),
                                          child: _isEditing
                                              ? SizedBox(
                                            width: 150,
                                            child: TextField(
                                              controller: _powerController,
                                              style: TextStyle(fontSize: 15.0),
                                              decoration: InputDecoration(
                                                border: InputBorder.none,
                                                contentPadding: EdgeInsets.symmetric(vertical: 10.0),
                                              ),
                                            ),
                                          )
                                              : Text(
                                            _powerController.text,
                                            style: TextStyle(fontSize: 15.0, fontWeight: FontWeight.bold, color: Colors.black),
                                          ),
                                        ),

                                      ]
                                  )
                              ),
                            ),
                          ]
                      ))
                    ]
                )
            ),
            const SizedBox(height: 30),
            SizedBox(
              width: 150,
              height: 45,
              child: ElevatedButton(
                onPressed: () {
                  Navigator.push(context,MaterialPageRoute(builder: (context)=>ListOfClientsAndSalesScreen(title: 'ListOfClientsAndSales')),);
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: Color(0xD9D9D9),
                  overlayColor: Color(0xFF00A8E8),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(65),
                  ),
                ),
                child: Text(
                  'Sprzedaj',
                  style: TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: 18,
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

}