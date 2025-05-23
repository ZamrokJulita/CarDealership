import re
from faker import Faker
from random import randint, choice
from datetime import datetime, timedelta
from dateutil.relativedelta import relativedelta
import json
import random
from copy import deepcopy
import os
import requests
import json

output_klienci = "carDealership/generator/clients.json"
output_samochody = "carDealership/generator/cars.json"
output_transakcje = "carDealership/generator/transactions.json"
output_enumy = "carDealership/generator/enums.json"
scraper_output = "carDealership/scraper/scraperResult.json"


fake = Faker("pl_PL")
klienci = []
samochody = []
silniki = []
transakcje = []

silniki_elektryczne = []
silniki_spalinowe = []

opisy_samochodow = {
    "elektryczny": [
        "Ten samochód elektryczny łączy nowoczesny design z bezemisyjną jazdą. Dzięki cichej pracy silnika i natychmiastowej dostępności momentu obrotowego zapewnia płynne przyspieszenie oraz wysoki komfort codziennego użytkowania.",
        "Wyposażony w zaawansowany układ napędowy, ten model oferuje zasięg odpowiedni zarówno do jazdy miejskiej, jak i tras międzymiastowych. Elektryczne BMW gwarantuje też niskie koszty eksploatacji i dostęp do najnowszych technologii.",
        "Nowoczesna platforma elektryczna pozwala na optymalne rozmieszczenie masy i wyjątkową stabilność prowadzenia. Ładowanie może odbywać się zarówno z domowego gniazdka, jak i szybkich stacji DC, co czyni ten samochód wyjątkowo wszechstronnym.",
        "Ten pojazd elektryczny został zaprojektowany z myślą o środowisku – bez spalin, hałasu i wibracji. Wnętrze oferuje nowoczesne wykończenie i cyfrowe funkcje, które uprzyjemniają każdą podróż.",
        "Dzięki nowoczesnym bateriom i wydajnemu systemowi odzyskiwania energii, auto sprawdza się doskonale w codziennym użytkowaniu, zapewniając jednocześnie dynamiczną jazdę bez kompromisów."
    ],
    "spalinowy": [
        "Samochód z klasycznym napędem spalinowym zapewnia długie zasięgi, szybkie tankowanie i szeroki zakres dostępnych mocy. To sprawdzony wybór dla osób podróżujących często i na dalekich dystansach.",
        "Silnik spalinowy oferuje naturalną responsywność i pełne osiągi bez konieczności ładowania. Pojazd świetnie sprawdza się w warunkach autostradowych oraz podczas intensywnej jazdy poza miastem.",
        "Dzięki dopracowanej technologii i niskiej emisji, nowoczesne silniki BMW spełniają rygorystyczne normy środowiskowe, jednocześnie oferując wysoką kulturę pracy i oszczędność paliwa.",
        "Wersje wysokoprężne to idealny wybór dla kierowców pokonujących długie trasy – zapewniają wysoki moment obrotowy i niskie spalanie, szczególnie przy jeździe w trasie.",
        "Silniki benzynowe BMW oferują dynamiczną jazdę, precyzyjną reakcję na gaz i szeroki wybór mocy. W połączeniu z zaawansowaną skrzynią biegów tworzą układ napędowy o sportowym charakterze."
    ],
    "hybrydowy": [
        "Samochód hybrydowy łączy moc silnika spalinowego z efektywnością napędu elektrycznego, zapewniając cichą jazdę w mieście i dynamiczne osiągi na trasie. Idealny kompromis między ekologią a zasięgiem.",
        "Dzięki możliwości jazdy w trybie czysto elektrycznym na krótkich dystansach, ten model pozwala ograniczyć zużycie paliwa i emisję CO₂. Przełączenie na tryb hybrydowy odbywa się płynnie i automatycznie.",
        "BMW z napędem hybrydowym oferuje elastyczność użytkowania – ładowanie z gniazdka lub stacji publicznej oraz możliwość korzystania z silnika spalinowego na dłuższych trasach bez stresu o zasięg.",
        "Zaawansowany system zarządzania energią pozwala inteligentnie wykorzystać oba źródła napędu. W połączeniu z typową dla BMW precyzją prowadzenia, auto oferuje komfort i oszczędność.",
        "Ten samochód hybrydowy to propozycja dla kierowców, którzy chcą zmniejszyć swój ślad węglowy bez rezygnacji z tradycyjnej dynamiki i zasięgu. Zintegrowane systemy wspomagające dbają o optymalną efektywność jazdy."
    ]
}

stan_samochodu = { "nowy", "używany" }

dane_samochodu = {
    "name",
    "model",
    "color",
    "acceleration",
    "transmission",
    "topSpeed",
    "gasMileage",
    "mileage",
    "drivetrainType",
    "description",
    "bodyType",
    "price",
    "imagePath",
    "vinNumber",
    "productionYear",
    "condition"
}

dane_silnikow_elektrycznych = {
    "power",
    "fuelType",
    "capacity"
}

dane_silnikow_spalinowych = {
    "power",
    "fuelType",
    "displacement",
    "cylindersNumber",
}

def filtrowanie_samochodow(sciezka_do_pliku):
    with open(sciezka_do_pliku, "r", encoding="utf-8") as f:
        data = json.load(f)

    elektryczne = []
    spalinowe = []
    hybrydowe = []

    for samochod in data:
        has_capacity = "capacity" in samochod
        has_displacement = "displacement" in samochod
        has_cylinders = "cylindersNumber" in samochod

        if has_capacity and has_displacement and has_cylinders:
            samochod["fuelType"] = "hybrydowy"
        elif has_capacity and not has_displacement and not has_cylinders:
            samochod["fuelType"] = "elektryczny"
        elif has_displacement and has_cylinders and not has_capacity:
            samochod["fuelType"] = random.choice(["benzyna", "diesel"])
        else:
            samochod["fuelType"] = "nieznany"

    spalinowe = [obj for obj in data if obj.get("fuelType") in ("benzyna", "diesel")]
    elektryczne = [obj for obj in data if obj.get("fuelType") == "elektryczny"]
    hybrydowe = [obj for obj in data if obj.get("fuelType") == "hybrydowy"]
    
    return elektryczne + spalinowe + hybrydowe


def generowanie_samochodu(samochod):

    if samochod["fuelType"] == "elektryczny":
        samochod["description"] = random.choice(opisy_samochodow["elektryczny"])
        samochod["gasMileage"] = round(random.uniform(13.0, 17.0), 2)
    elif samochod["fuelType"] in ("benzyna", "diesel"):
        samochod["description"] = random.choice(opisy_samochodow["spalinowy"])
        samochod["gasMileage"] = round(random.uniform(5.0, 10.0), 2)
    else:
        samochod["description"] = random.choice(opisy_samochodow["hybrydowy"])
        samochod["gasMileage"] = round(random.uniform(3.0, 6.0), 2)
    
    samochod["vinNumber"] = fake.unique.vin()
    samochod["productionYear"] = random.randint(2020, 2025)
    samochod["condition"] = random.choice(list(stan_samochodu))

    if samochod["condition"] == "nowy":
        samochod["mileage"] = 0
    else:
        samochod["mileage"] = random.randint(0, 40000)


    match = re.search(r'\((\d+)\)', samochod["power"])
    if "capacity" in samochod:
        samochod["capacity"] = float(samochod["capacity"].replace(",", ".").strip())

    if "displacement" in samochod:
        samochod["displacement"] = int(samochod["displacement"].replace(" ", "").strip())

    acceleration_match = re.search(r'\d+,\d+', samochod["acceleration"])
    if acceleration_match:
        samochod["acceleration"] = float(acceleration_match.group(0).replace(',', '.'))
    else:
        samochod["acceleration"] = float(samochod["acceleration"].replace(",", ".").strip())

    


    if match:
        samochod["power"] = int(match.group(1))
    
    silniki = filtrowanie_silnika(samochod)
    
    samochod = {k: v for k, v in samochod.items() if k in dane_samochodu}

    samochod["engines"] = silniki

    return samochod


def filtrowanie_silnika(samochod):

    silniki = []

    if samochod["fuelType"] == "elektryczny":
        silnik = {
            "type": "electric",
            "power": samochod["power"],
            "fuelType": samochod["fuelType"],
            "capacity": samochod["capacity"]
        }
        silniki.append(silnik)
    elif samochod["fuelType"] in ("benzyna", "diesel"):
        silnik = {
            "type": "combustion",
            "power": samochod["power"],
            "fuelType": samochod["fuelType"],
            "displacement": samochod["displacement"],
            "cylindersNumber": samochod["cylindersNumber"]
        }
        silniki.append(silnik)
    elif samochod["fuelType"] == "hybrydowy":

        silnik_elektryczny = {
            "type": "electric",
            "power": samochod["power"],
            "fuelType": "elektryczny",
            "capacity": samochod["capacity"]
        }
        silniki.append(silnik_elektryczny)

        silnik_spalinowy = {
            "type": "combustion",
            "power": samochod["power"],
            "fuelType": random.choice(["benzyna", "diesel"]),
            "displacement": samochod["displacement"],
            "cylindersNumber": samochod["cylindersNumber"]
        }
        silniki.append(silnik_spalinowy)

    return silniki


# szukanie wartosci do enumow
def znajdz_bodyType(samochody):
    bodyType = set()
    for samochod in samochody:
        bodyType.add(samochod["bodyType"])
    return list(bodyType)

def znajdz_condition(samochody):
    condition = set()
    for samochod in samochody:
        condition.add(samochod["condition"])
    return list(condition)

def znajdz_drivetrainType(samochody):
    drivetrainType = set()
    for samochod in samochody:
        drivetrainType.add(samochod["drivetrainType"])
    return list(drivetrainType)

def znajdz_transmission(samochody):
    transmission = set()
    for samochod in samochody:
        transmission.add(samochod["transmission"])
    return list(transmission)

def znajdz_fuelType(samochody):
    fuelType = set()
    for samochod in samochody:
        for silnik in samochod["engines"]:
            fuelType.add(silnik["fuelType"])
    return list(fuelType)

def znajdz_paymentMethod(transakcje):
    paymentMethod = set()
    for transakcja in transakcje:
        paymentMethod.add(transakcja["paymentMethod"])
    return list(paymentMethod)

def generowanie_transakcji():

    all_avaliable_samochody = requests.get("http://localhost:8080/api/cars").json()
    all_avaliable_klienci = requests.get("http://localhost:8080/api/clients").json()
    
    transakcje = []

    for _ in range(25):

        car = random.choice(all_avaliable_samochody)
        all_avaliable_samochody.remove(car)
        client = random.choice(all_avaliable_klienci)
        all_avaliable_klienci.remove(client)

        car_id = car["id"]
        client_id = client["id"]

        payment_method = random.choice(["gotówka", "karta kredytowa", "przelew"])

        total_amount = car["price"]

        registered = random.choice([True, False])
        insured = random.choice([True, False])

        start_date = datetime(car["productionYear"], 1, 1) + relativedelta(months=2)
        transactionDate = fake.date_time_between(start_date=start_date, end_date="now")
        transactionDate_str = transactionDate.strftime("%Y-%m-%dT%H:%M:%S")

        # Dodaj transakcję do listy
        transakcje.append({
            "transactionDate": transactionDate_str,
            "totalAmount": total_amount,
            "paymentMethod": payment_method,
            "registered": registered,
            "insured": insured,
            "clientId": client_id,
            "carId": car_id
        })
        
    return transakcje


transakcje = generowanie_transakcji()

with open(output_transakcje, "w", encoding="utf-8") as f:
    json.dump(transakcje, f, ensure_ascii=False, indent=2)

# def generowanie_klienta_indywidualnego():
#     type = "individual"
#     name = fake.first_name()
#     surname = fake.last_name()
#     pesel = fake.random_int(min=100000000, max=999999999)
#     address = fake.address().replace("\n", ", ")
#     phoneNumber = fake.phone_number()
#     email = fake.email()
#     return {
#         "type": type,
#         "name": name,
#         "surname": surname,
#         "pesel": pesel,
#         "address": address,
#         "phoneNumber": phoneNumber,
#         "email": email
#     }
    
# def generowanie_klienta_firmowego():
#     type = "corporate"
#     companyName = fake.company()
#     nip = fake.random_int(min=100000000, max=999999999)
#     address = fake.address().replace("\n", ", ")
#     phoneNumber = fake.phone_number()
#     email = fake.email()

#     return {
#         "type": type,
#         "companyName": companyName,
#         "nip": nip,
#         "address": address,
#         "phoneNumber": phoneNumber,
#         "email": email
#     }

# for i in range(100):
#     if i % 2 == 0:
#         klienci.append(generowanie_klienta_indywidualnego())
#     else:
#         klienci.append(generowanie_klienta_firmowego())

# samochody_po_filtrowaniu = filtrowanie_samochodow(scraper_output)

# for samochod in samochody_po_filtrowaniu:
#     samochod = generowanie_samochodu(samochod)
#     samochody.append(samochod)

# with open(output_samochody, "w", encoding="utf-8") as f:
#     json.dump(samochody, f, ensure_ascii=False, indent=2)



# bodyType = znajdz_bodyType(samochody)
# condition = znajdz_condition(samochody)
# drivetrainType = znajdz_drivetrainType(samochody)
# transmission = znajdz_transmission(samochody)
# silniki = znajdz_fuelType(samochody)
# paymentMethod = znajdz_paymentMethod(transakcje)

# enum = {
#     "bodyType": bodyType,
#     "condition": condition,
#     "drivetrainType": drivetrainType,
#     "transmission": transmission,
#     "fuelType": silniki,
#     "paymentMethod": paymentMethod
# }

# with open(output_enumy, "w", encoding="utf-8") as f:
#     json.dump(enum, f, ensure_ascii=False, indent=2)

# with open(output_klienci, "w", encoding="utf-8") as f:
#     json.dump(klienci, f, ensure_ascii=False, indent=2)