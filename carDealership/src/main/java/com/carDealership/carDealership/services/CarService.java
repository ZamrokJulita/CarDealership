package com.carDealership.carDealership.services;

import com.carDealership.carDealership.dto.car.CarCreateDto;
import com.carDealership.carDealership.dto.car.CarReadDto;
import com.carDealership.carDealership.dto.car.CarUpdateDto;
import com.carDealership.carDealership.models.Car;
import com.carDealership.carDealership.models.Engine;
import com.carDealership.carDealership.repositories.CarRepository;
import com.carDealership.carDealership.repositories.EngineRepository;
import com.carDealership.carDealership.specifications.CarSpecifications;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final EngineRepository engineRepository;
    private final FileStorageService fileStorageService;
    @PersistenceContext
    private EntityManager entityManager;

    public CarService(CarRepository carRepository, EngineRepository engineRepository, FileStorageService fileStorageService, EntityManager entityManager) {
        this.carRepository = carRepository;
        this.engineRepository = engineRepository;
        this.fileStorageService = fileStorageService;
        this.entityManager = entityManager;
    }

    public List<CarReadDto> getAllCars() {
        return this.carRepository.findAllByOrderByIdAsc()
                .stream()
                .map(CarReadDto::new)
                .collect(Collectors.toList());
    }

    public CarReadDto getCarById(Integer id) {
        return this.carRepository.findById(id)
                .map(CarReadDto::new)
                .orElse(null);
    }

    public List<CarReadDto> getAllFilteredCars(Map<String, String> filters) {
        Specification<Car> spec = CarSpecifications.carModelWithFilters(filters);
        List<Car> result = carRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "id"));

        return result
                .stream()
                .map(CarReadDto::new)
                .collect(Collectors.toList());
    }

    public CarReadDto save(CarCreateDto carCreateDto, MultipartFile imageFile) {
        var imagePath = fileStorageService.storeImage(imageFile);
        var carEntity = carCreateDto.convertToCar();
        carEntity.setImagePath(imagePath);
        var result = carRepository.save(carEntity);
        var carDto = new CarReadDto(result);
        carDto.setImagePath(imagePath);
        return carDto;
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
