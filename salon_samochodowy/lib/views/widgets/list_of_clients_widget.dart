import 'package:flutter/material.dart';
import '../screens/list_of_clients_screen.dart';

class ListOfClientsWidget extends StatelessWidget {
  final List<Client> filteredClients;
  final Function(String) onSearch;

  const ListOfClientsWidget({
    super.key,
    required this.filteredClients,
    required this.onSearch,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(16.0),
          child: Text(
            'Lista klientów',
            style: TextStyle(
              fontSize: 20.0,
              fontWeight: FontWeight.bold,
              color: Colors.blue,
            ),
            textAlign: TextAlign.center,
          ),
        ),
        Padding(
          padding: const EdgeInsets.all(8.0),
          child: TextField(
            onChanged: onSearch,
            decoration: InputDecoration(
              labelText: 'Wyszukaj po nazwisku',
              labelStyle: TextStyle(color: Colors.grey),
              prefixIcon: Icon(Icons.search),
              focusedBorder: OutlineInputBorder(
                borderSide: BorderSide(color: Color(0xFF00A8E8), width: 2.0),
              ),
              enabledBorder: OutlineInputBorder(
                borderSide: BorderSide(color: Colors.grey, width: 1.0),
              ),
            ),
          ),
        ),
        Expanded(
          child: ListView.builder(
            itemCount: filteredClients.length,
            itemBuilder: (context, index) {
              final client = filteredClients[index];
              return Padding(
                padding: const EdgeInsets.only(top: 10.0),
                child: Center(
                  child: Container(
                    width: 350,
                    height: 100,
                    decoration: BoxDecoration(
                      color: const Color(0xFFD9D9D9),
                      borderRadius: BorderRadius.circular(20),
                    ),
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 20.0, vertical: 15.0), // Przesunięcie do środka
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween, // Rozstawienie na boki
                        children: [
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.start, // Wyrównanie do lewej
                            mainAxisAlignment: MainAxisAlignment.center, // Wyśrodkowanie w pionie
                            children: [
                              Text(
                                '${client.firstName} ${client.lastName}',
                                style: const TextStyle(
                                  fontSize: 18,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black,
                                ),
                              ),
                              const SizedBox(height: 5), // Odstęp między nazwiskiem a emailem
                              Text(
                                client.email,
                                style: const TextStyle(
                                  fontSize: 16,
                                  color: Colors.black54,
                                ),
                              ),
                            ],
                          ),
                          Text(
                            client.phone, // Numer telefonu po prawej
                            style: const TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                              color: Colors.black87,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              );
            },
          ),
        ),
      ],
    );
  }
}
