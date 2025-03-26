package com.carDealership.carDealership.dto.car;

import com.carDealership.carDealership.dto.engine.EngineReadDto;
import com.carDealership.carDealership.models.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarReadDto {

    int id;

    String name;

    String model;

    String color;

    double acceleration;

    String transmission;

    double topSpeed;

    double gasMileage;

    String drivetrainType;

    String description;

    String bodyType;

    BigDecimal price;

    String imagePath;

    String vinNumber;

    int productionYear;

    List<EngineReadDto> engines;

    public CarReadDto(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.model = car.getModel().toString();
        this.color = car.getColor();
        this.acceleration = car.getAcceleration();
        this.transmission = car.getTransmission().toString();
        this.topSpeed = car.getTopSpeed();
        this.gasMileage = car.getGasMileage();
        this.drivetrainType = car.getDrivetrainType().toString();
        this.description = car.getDescription();
        this.bodyType = car.getBodyType().toString();
        this.price = car.getPrice();
        this.imagePath = car.getImagePath();
        this.vinNumber = car.getVinNumber();
        this.productionYear = car.getProductionYear();
        this.engines = car.getEngines()
                .stream()
                .map(EngineReadDto::new)
                .toList();
    }
}
