import json
import requests

input_file = 'carDealership/generator/clients.json'

with open(input_file, 'r') as file:
    clients = json.load(file)

    for client in clients:
        url = 'http://localhost:8080/api/clients'
        response = requests.post(url, json=client)
        if response.status_code == 201:
            print(f"Client uploaded successfully.")
        else:
            print(f"Failed to upload client: {response.status_code}")
            print(f"Response: {response.text}")


