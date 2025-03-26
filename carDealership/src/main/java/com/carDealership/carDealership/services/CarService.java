package com.carDealership.carDealership.services;

import com.carDealership.carDealership.dto.car.CarCreateDto;
import com.carDealership.carDealership.dto.car.CarReadDto;
import com.carDealership.carDealership.dto.car.CarUpdateDto;
import com.carDealership.carDealership.models.Car;
import com.carDealership.carDealership.models.Engine;
import com.carDealership.carDealership.repositories.CarRepository;
import com.carDealership.carDealership.repositories.EngineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final EngineRepository engineRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public CarService(CarRepository carRepository, EngineRepository engineRepository, EntityManager entityManager) {
        this.carRepository = carRepository;
        this.engineRepository = engineRepository;
        this.entityManager = entityManager;
    }

    public List<CarReadDto> getAllCars() {
        return this.carRepository.findAll()
                .stream()
                .map(CarReadDto::new)
                .collect(Collectors.toList());
    }

    public CarReadDto save(CarCreateDto carCreateDto) {
        var result = carRepository.save(carCreateDto.convertToCar());
        return new CarReadDto(result);
    }

    public void delete(int idCar) {
        if (this.carRepository.existsById(idCar)) {
            this.carRepository.deleteById(idCar);
            return;
        }
        throw new IllegalArgumentException();
    }

    public CarReadDto addEngineToCar(int idCar, int idEngine) {
        Car car = this.carRepository.findById(idCar).orElseThrow();
        Engine engine = this.engineRepository.findById(idEngine).orElseThrow();

        car.getEngines().add(engine);
        carRepository.save(car);

        return new CarReadDto(car);
    }

    @Transactional
    public CarReadDto updateCar(CarUpdateDto carUpdateDto) {
        Car updatedCar = this.entityManager.merge(carUpdateDto.convertToCar());
        return new CarReadDto(updatedCar);
    }
}
