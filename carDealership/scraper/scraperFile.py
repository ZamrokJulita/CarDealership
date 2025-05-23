from concurrent.futures import ThreadPoolExecutor, as_completed
from itertools import takewhile
import os
import requests
import uuid
import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
import random


output_file = "carDealership/scraper/scraperResult_TEST.json"
photos_dir = "carDealership/scraper/photosTEST/"
page_url = "https://www.bmw.pl/pl/all-models.html"

options = Options()
options.add_argument("--headless=new")
options.add_argument("--disable-gpu")
options.add_argument("--no-sandbox")
options.add_argument("--window-size=1920,1080")
options.add_argument("--incognito")
# options.add_argument("--disable-blink-features=AutomationControlled")  # <-- ważne
# options.add_experimental_option("excludeSwitches", ["enable-automation"])
# options.add_experimental_option("useAutomationExtension", False)


dozwolone_wartosci = {
  "price" : "price",
  "fuelType" : "fuelType",
  "bodyType" : "bodyType",
  "name" : "name",
  "model" : "model",
  "image_path" : "imagePath",
  "color" : "color",
  "Moc w kW (KM)" : "power",
  "Skrzynia biegów" : "transmission",
  "Rodzaj napędu" : "drivetrainType",
  "Cylindry" : "cylindersNumber",
  "Pojemność w cm³" : "displacement",
  "Przyspieszenie 0-100 km/h w s" : "acceleration",
  "Prędkość maksymalna w km/h" : "topSpeed",
  "Pojemność akumulatora w kWh" : "capacity"
}


# Initialize driver
driver = webdriver.Chrome(options=options)
# driver.execute_cdp_cmd(
#     "Page.addScriptToEvaluateOnNewDocument",
#     {
#         "source": """
#             Object.defineProperty(navigator, 'webdriver', {
#                 get: () => undefined
#             })
#         """
#     }
# )
driver.get(page_url)

cars = driver.find_elements(By.CLASS_NAME, "allmodelscard.container.responsivegrid[data-counter]")
#[os.remove(os.path.join(photos_dir, f)) for f in os.listdir(photos_dir) if os.path.isfile(os.path.join(photos_dir, f))]

wanted_cars = []

for car in cars:
    value = int(car.get_attribute("data-counter"))
    
    if 1400 <= value <= 10400:
        wanted_cars.append(car)


cars_with_links = []

for wanted_car in wanted_cars:
    div = wanted_car.find_element(By.TAG_NAME, "div")
    div_attributes = div.get_attribute("data-card-filter-info")
    data = json.loads(div_attributes)

    car_info = {}
    car_info["price"] = data["price"]

    #fuelType trzeba pobierac ze strony juz danego samochodu bo tu sie zle pobiera
    # car_info["fuelType"] = {
    #     "o" : "benzyna",
    #     "e" : "elektryczny",
    #     "d" : "diesel",
    #     "h" : "hybrydowy"
    # }.get(data["fuelType"])

    car_info["bodyType"] = {
        "li" : "limuzyna",
        "to" : "combi",
        "cp" : "coupe",
        "suv" : "suv",
        "caro" : "cabriolet",
        "comp" : "compact"
    }.get(data["category"])

    div_details = div.find_element(By.CLASS_NAME,"cmp-allmodelscarddetail").get_attribute("data-tracking-attributes")
    data_details =json.loads(div_details)
    car_info["name"] = data_details["name"]
    
    car_link = wanted_car.find_element(By.XPATH, './/a[@aria-label="Informacje o pojeździe"]').get_attribute("href")
    car_info["link"] = car_link

    cars_with_links.append(car_info)


# po wykonaniu tego wyzej mam liste samochodow z podstawowymi danymi i linkiem do wejscia w ich strone ze szczegolami

def process_car(idx, car, filtered_cars):
    filtered_car = {}
    try:
        driver = webdriver.Chrome(options=options)
        print(f"\n=== [Krok {idx + 1}/{len(cars_with_links)}] ===")
        print(f"Przetwarzam: {car.get('name')} - {car.get('link')}")

        driver.get(car["link"])
        time.sleep(2)
        driver.execute_script("window.scrollBy(0, window.innerHeight * 3);")
        time.sleep(2)

        src_image = None
        color_text = ""
        try:
            img = driver.find_element(
                By.XPATH,
                '//div[contains(@class, "cmp-modelhubcard__image") and contains(@class, "cmp-modelhubcard__image--frontview") and contains(@class, "cmp-modelhubcard__image--disable-hover")]//source[@type="image/webp" and @media="(max-width: 1024px)"]'
            )
            src_image = img.get_attribute("srcset")
            print("✔️ Zdjęcie znalezione w standardowy sposób (srcset).")
        except:
            try:
                wait = WebDriverWait(driver, 15)
                image = wait.until(EC.presence_of_element_located((
                    By.CSS_SELECTOR,
                    "div.view-car.tw-absolute.tw-pin-t.tw-pin-l img"
                )))
                src_image = image.get_attribute("src")
                alt_raw = image.get_attribute("alt") or ""
                color_text = alt_raw.replace("undefined", "").strip()
                print("✔️ Zdjęcie znalezione w <div class='view-car ...'>")
            except:
                print("❌ Nie udało się znaleźć zdjęcia alternatywnie.")

        if src_image:
            try:
                response = requests.get(src_image)
                if response.status_code == 200:
                    image_path = f"{os.path.join(photos_dir, uuid.uuid4().hex)}.png"
                    car["image_path"] = image_path
                    # with open(image_path, "wb") as f:
                    #     f.write(response.content)
                    # print("✔️ Zdjęcie zapisane:", image_path)
            except Exception as e:
                print(f"❌ Błąd przy pobieraniu zdjęcia.")

        # Color
        if not color_text:
            try:
                wait = WebDriverWait(driver, 5)
                color_span = wait.until(EC.presence_of_element_located((
                    By.CSS_SELECTOR,
                    "button.cmp-visualizer__model-option--selected + div.cmp-visualizer__option-label span.cmp-visualizer__option-label--multiline"
                )))
                color_text = color_span.get_attribute("textContent").strip()
                print("✔️ Kolor:", color_text)
            except:
                pass
        car["color"] = color_text

        # Technical link
        try:
            # technical_link_element = WebDriverWait(driver, 5).until(
            #     EC.presence_of_element_located((By.XPATH, './/a[@aria-label="Dane techniczne"]'))
            # )
            # car["technical_link"] = technical_link_element.get_attribute("href")
            
            button = WebDriverWait(driver, 10).until(
                EC.visibility_of_element_located((By.XPATH, '//a[@aria-label="Dane techniczne"]'))
            )
            print("✔️ Przycisk widoczny.")
            # Czekaj aż zasłaniający div zniknie
            try:
                WebDriverWait(driver, 5).until(
                    EC.invisibility_of_element_located((By.CLASS_NAME, "cmp-contentnavigation__wrapper"))
                )
                print("✅ Zasłaniający element zniknął.")
            except:
                print("⚠️ Zasłaniający element nie zniknął – próbuję mimo to.")
            driver.execute_script("arguments[0].scrollIntoView(true);", button)
            time.sleep(1)
            driver.execute_script("arguments[0].click();", button)
            time.sleep(5)
            print("✅ Kliknięto przycisk (przez JS)")
            WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.CLASS_NAME, "cmp-technicaldatafact"))
            )
            # Poczekaj aż dane się załadują
            # Parsuj dane
            filtered_car = parse_technical_data_TEST(driver, car)
            filtered_car["technical_data_loaded"] = True
            print("✅ Dane techniczne załadowane za pomoca GUZIKA.")
            #filtered_cars.append(car)
            # Tylko jeśli dane załadowano guzikiem, dodaj do filtered_cars:
            if filtered_car.get("technical_data_loaded") is True:
                # filtered_car = {
                #     dozwolone_wartosci.get(k, k): v
                #     for k, v in car.items()
                #     if k in dozwolone_wartosci
                # }
                filtered_cars.append(filtered_car)
            
        except Exception as e:
            print(f"❌ Błąd przy klikaniu w przycisk, probuje inaczej.")
            try:
                xpath = (
                    "//div[contains(@class, 'ds2-model-brief--table') and contains(@class, 'large-push-6')]"
                    "//ul/li/a[span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŹŻ', 'abcdefghijklmnopqrstuvwxyząćęłńóśźż'), 'dane techniczne')]]"
                )
                link = WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH, xpath)))
                car["technical_link"] = link.get_attribute("href")
                car["technical_data_loaded"] = False
            except Exception as e:
                print(f"❌ Nie udało się pobrać linku technicznego alternatywnie.")
                car["technical_link"] = ""
                car["technical_data_loaded"] = False

        # filtered_car = {
        #     dozwolone_wartosci.get(k, k): v
        #     for k, v in car.items()
        #     if k in dozwolone_wartosci
        # }
    except Exception as e:
        print(f"❌ Błąd ogólny.")
    finally:
        driver.quit()
    return car
    


def parse_technical_data_TEST(driver, car):
    try:
        technical_data_facts = driver.find_elements(By.CSS_SELECTOR, "tr.cmp-technicaldatafact")
        print(f"🔍 Znaleziono {len(technical_data_facts)} elementów .cmp-technicaldatafact")

        for fact in technical_data_facts:
            try:
                key = fact.find_element(By.CSS_SELECTOR, "td.cmp-technicaldatafact__label p.cmp-text__paragraph").text.strip()
                value = fact.find_element(By.CSS_SELECTOR, "td.cmp-technicaldatafact__value p.cmp-text__paragraph").text.strip()
                #print(f"✅ {key} -> {value}")
                car[key] = value
            except Exception as e:
                print(f"❌ Błąd przy elemencie fact: {e}")
                continue

        result = " ".join(takewhile(lambda x: "drive" not in x.lower(), car["name"].split()[1:]))
        car["model"] = result

        filtered_car = {
            dozwolone_wartosci[k]: v
            for k, v in car.items()
            if k in dozwolone_wartosci
        }

        # === Określenie fuelType ===
        has_capacity = "capacity" in filtered_car
        has_displacement = "displacement" in filtered_car
        has_cylinders = "cylindersNumber" in filtered_car

        if has_capacity and has_displacement and has_cylinders:
            filtered_car["fuelType"] = "hybrydowy"
        elif has_capacity and not has_displacement and not has_cylinders:
            filtered_car["fuelType"] = "elektryczny"
        elif has_displacement and has_cylinders and not has_capacity:
            filtered_car["fuelType"] = random.choice(["benzyna", "diesel"])
        else:
            filtered_car["fuelType"] = "nieznany"
    
    except Exception as e:
        print(f"❌ Błąd główny przy parsowaniu danych technicznych: {e}")

    return filtered_car


processed_cars = []
filtered_cars = []

with ThreadPoolExecutor(max_workers=os.cpu_count()) as executor:
    futures = [executor.submit(process_car, idx, car, filtered_cars) for idx, car in enumerate(cars_with_links)]

    for future in as_completed(futures):
        processed_cars.append(future.result())

print("✅ Gotowe! Przetworzono:", len(processed_cars))




for car in processed_cars:
    if not car.get("technical_link"):
        continue

    if car.get("technical_data_loaded") == False:
        driver.get(car["technical_link"])

        technical_data_facts = driver.find_elements(By.CLASS_NAME, "cmp-technicaldatafact")
        for fact in technical_data_facts:
            key = fact.find_element(By.CSS_SELECTOR, "td.cmp-technicaldatafact__label p.cmp-text__paragraph").text.strip()
            value = fact.find_element(By.CSS_SELECTOR, "td.cmp-technicaldatafact__value p.cmp-text__paragraph").text.strip()
            car[key] = value

        result = " ".join(takewhile(lambda x: "drive" not in x.lower(), car["name"].split()[1:]))
        car["model"] = result

    
    filtered_car = {
        dozwolone_wartosci[k]: v
        for k, v in car.items()
        if k in dozwolone_wartosci
    }

    # === Określenie fuelType ===
    has_capacity = "capacity" in filtered_car
    has_displacement = "displacement" in filtered_car
    has_cylinders = "cylindersNumber" in filtered_car

    if has_capacity and has_displacement and has_cylinders:
        filtered_car["fuelType"] = "hybrydowy"
    elif has_capacity and not has_displacement and not has_cylinders:
        filtered_car["fuelType"] = "elektryczny"
    elif has_displacement and has_cylinders and not has_capacity:
        filtered_car["fuelType"] = random.choice(["benzyna", "diesel"])
    else:
        filtered_car["fuelType"] = "nieznany"


    filtered_cars.append(filtered_car)


with open(output_file,"w",encoding="utf-8") as f:
    json.dump(filtered_cars, f,ensure_ascii=False, indent=2)



#     test_car = {
#     "name": "TEST - POWINNO BYC CYLINDRY",
#     "link": "https://www.bmw.pl/pl/all-models/bmw-serii-5-w-skrocie/5-series-touring/bmw-serii-5-touring.html#technical-data",
#     "price": "230000",
#     "fuelType": "elektryczny",
#     "bodyType": "suv"
# }

# processed = process_car(0, test_car)

# #print(json.dumps(processed, indent=2, ensure_ascii=False))