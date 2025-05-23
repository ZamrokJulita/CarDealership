package com.carDealership.carDealership.controllers;

import com.carDealership.carDealership.dto.car.CarCreateDto;
import com.carDealership.carDealership.dto.car.CarReadDto;
import com.carDealership.carDealership.dto.car.CarUpdateDto;
import com.carDealership.carDealership.services.CarService;
import com.carDealership.carDealership.services.EngineService;
import jakarta.annotation.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;
    EngineService engineService;

    public CarController(EngineService engineService, CarService carService) {
        this.carService = carService;
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<List<CarReadDto>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{carId}/image")
    public ResponseEntity<byte[]> getCarImage(@PathVariable int carId) {
        try{
            //var carmememe = carService.getAllCars().stream().filter(c -> c.getId() == carId).findFirst().orElse(null);
            var car = carService.getCarById(carId);
            if(car == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Path imagePath = Paths.get("uploads", car.getImagePath()); // e.g., uploads/abc123.png

            if (!Files.exists(imagePath)) {
                return ResponseEntity.notFound().build();
            }

            byte[] imageBytes = Files.readAllBytes(imagePath);
            String contentType = Files.probeContentType(imagePath); // e.g., image/jpeg

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/filters")
    public ResponseEntity<List<CarReadDto>> getAllFilteredCars(@RequestParam Map<String, String> filters) {
        return new ResponseEntity<>(carService.getAllFilteredCars(filters), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarReadDto> createCar(@RequestPart("car") CarCreateDto carCreateDto, @RequestPart("image") MultipartFile imageFile) {
        CarReadDto carReadDto = carService.save(carCreateDto, imageFile);
        return new ResponseEntity<>(carReadDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable int carId) {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{carId}/add-engine/{engineId}")
    public ResponseEntity<CarReadDto> addEngineToCar(@PathVariable int carId, @PathVariable int engineId) {
        CarReadDto carReadDto = this.carService.addEngineToCar(carId, engineId);
        return new ResponseEntity<>(carReadDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CarReadDto> updateCar(@RequestBody CarUpdateDto carUpdateDto) {
        CarReadDto carReadDto = this.carService.updateCar(carUpdateDto);
        return new ResponseEntity<>(carReadDto, HttpStatus.OK);
    }
}
