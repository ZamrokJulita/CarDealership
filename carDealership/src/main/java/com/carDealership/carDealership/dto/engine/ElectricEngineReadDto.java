package com.carDealership.carDealership.dto.engine;

import com.carDealership.carDealership.models.ElectricEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElectricEngineReadDto extends EngineReadDto {
    double capacity;

    public ElectricEngineReadDto(ElectricEngine engine) {
        super(engine);
        this.capacity = engine.getCapacity();
    }

}
