import requests
import json
import os

input_file = 'carDealership/generator/transactions.json'

with open(input_file, 'r') as file:
    data = json.load(file)

def upload_transakcji(transakcje):
    url = 'http://localhost:8080/api/transactions'
    
    for transakcja in transakcje:
        response = requests.post(url, json=transakcja)
        if response.status_code == 201:
            print(f"Transaction uploaded successfully.")
        else:
            print(f"Error: {response.status_code}")
            print(f"Response: {response.text}")
            break
            print(f"FAILED to upload transaction.")

upload_transakcji(data)