package com.carDealership.carDealership.dto.engine;

import com.carDealership.carDealership.models.Engine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EngineReadDto {
    int id;
    int power;
    String fuelType;

    public EngineReadDto(Engine engine) {
        this.id = engine.getId();
        this.power = engine.getPower();
        this.fuelType = engine.getFuelType().toString();
    }
}
