import 'package:flutter/material.dart';
import '../widgets/header_widget.dart';
import '../widgets/list_of_clients_widget.dart';
import '../models/client_mock.dart';

class ListOfClientsScreen extends StatefulWidget {
  const ListOfClientsScreen({super.key, required String title});

  @override
  _ListOfClientsScreenState createState() => _ListOfClientsScreenState();
}

class _ListOfClientsScreenState extends State<ListOfClientsScreen> {
  List<Client> clients = [
    Client(firstName: 'Jan', lastName: 'Kowalski', email: 'jan@example.com', phone: '123456789'),
    Client(firstName: 'Anna', lastName: 'Nowak', email: 'anna@example.com', phone: '987654321'),
    Client(firstName: 'Piotr', lastName: 'Zieliński', email: 'piotr@example.com', phone: '564738291'),
  ];

  List<Client> filteredClients = [];

  @override
  void initState() {
    super.initState();
    filteredClients = clients;
  }

  void filterClients(String query) {
    final results = clients.where((client) {
      return client.lastName.toLowerCase().contains(query.toLowerCase());
    }).toList();

    setState(() {
      filteredClients = results;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: const Size.fromHeight(80),
        child: HeaderWidget(),
      ),
      body: ListOfClientsWidget(
        filteredClients: filteredClients,
        onSearch: filterClients,
      ),
    );
  }
}
