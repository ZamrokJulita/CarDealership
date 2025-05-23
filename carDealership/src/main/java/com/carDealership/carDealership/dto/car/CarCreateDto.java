package com.carDealership.carDealership.dto.car;

import com.carDealership.carDealership.enums.*;
import com.carDealership.carDealership.models.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarCreateDto {
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

    String vinNumber;

    int productionYear;

    int mileage;

    String condition;

    public Car convertToCar() {
        Car car = new Car();
        car.setName(this.name);
        car.setModel(this.model);
        car.setColor(this.color);
        car.setAcceleration(this.acceleration);
        car.setTransmission(CarTransmission.fromDisplayName(this.transmission));
        car.setTopSpeed(this.topSpeed);
        car.setGasMileage(this.gasMileage);
        car.setDrivetrainType(CarDrivetrainType.fromDisplayName(this.drivetrainType));
        car.setDescription(this.description);
        car.setBodyType(CarBodyType.valueOf(this.bodyType));
        car.setPrice(this.price);
        car.setVinNumber(this.vinNumber);
        car.setProductionYear(this.productionYear);
        car.setMileage(this.mileage);
        car.setCondition(CarCondition.valueOf(this.condition));
        car.setEngines(new ArrayList<>());
        return car;
    }
}
