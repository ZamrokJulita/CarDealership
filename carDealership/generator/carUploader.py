import requests
import json
import os

input_file = 'carDealership/generator/cars.json'
images_dir = 'carDealership/scraper/photos'

def upload_samochod(samochod):
    url = 'http://localhost:8080/api/cars'
    imagePath = samochod.pop('imagePath')
    request = {
        "car": ("car.json", json.dumps(samochod), 'application/json'),
        "image": (os.path.basename(imagePath), open(imagePath, 'rb'), 'image/png')
    }
    response = requests.post(url, files=request)
    if response.status_code == 201:
        print(f"Car uploaded successfully.")
        return response.json().get('id')
    else:
        print(f"Failed to upload car: {response.status_code}")
        print(f"Response: {response.text}")


def upload_silnik(silnik):
    url = 'http://localhost:8080/api/engines'
    response = requests.post(url, json=silnik)
    if response.status_code == 201:
        print(f"Engine uploaded successfully.")
        return response.json().get('id')
    else:
        print(f"Failed to upload engine: {response.status_code}")
        print(f"Response: {response.text}")
    
def polacz_samochod_z_silnikami(id_samochodu, id_silnikow):
    for id_silnika in id_silnikow:
        url = f'http://localhost:8080/api/cars/{id_samochodu}/add-engine/{id_silnika}'
        response = requests.put(url)
        if response.status_code == 200:
            print(f"Car {id_samochodu} connected to engine {id_silnika} successfully.")
        else:
            print(f"Failed to connect car {id_samochodu} to engine {id_silnika}: {response.status_code}")
            print(f"Response: {response.text}")
    

with open(input_file, 'r') as file:
    samochody = json.load(file)

    for samochod in samochody:
        url = 'http://localhost:8080/api/cars'
        
        engines = samochod.pop('engines')
        engines_ids = []

        for silnik in engines:
            silnik_id = upload_silnik(silnik)
            if silnik_id:
                engines_ids.append(silnik_id)
        
        id_samochodu = upload_samochod(samochod)

        if id_samochodu and engines_ids:
            polacz_samochod_z_silnikami(id_samochodu, engines_ids)
        else:
            print(f"FAILED to upload car or engines for car.")