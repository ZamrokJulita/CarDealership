package com.carDealership.carDealership.controllers;

import com.carDealership.carDealership.dto.car.CarCreateDto;
import com.carDealership.carDealership.dto.car.CarReadDto;
import com.carDealership.carDealership.dto.car.CarUpdateDto;
import com.carDealership.carDealership.services.CarService;
import com.carDealership.carDealership.services.EngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<CarReadDto> createCar(@RequestBody CarCreateDto carCreateDto) {
        CarReadDto carReadDto = carService.save(carCreateDto);
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
