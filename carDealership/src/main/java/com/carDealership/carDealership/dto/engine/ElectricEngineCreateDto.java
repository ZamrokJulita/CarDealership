package com.carDealership.carDealership.dto.engine;

import com.carDealership.carDealership.enums.EngineFuelType;
import com.carDealership.carDealership.models.ElectricEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElectricEngineCreateDto extends EngineCreateDto {
    double capacity;

    @Override
    public ElectricEngine convertToEngine() {
        ElectricEngine electricEngine = new ElectricEngine();
        electricEngine.setPower(this.getPower());
        electricEngine.setFuelType(EngineFuelType.valueOf(this.getFuelType()));
        electricEngine.setCapacity(this.getCapacity());
        electricEngine.setCars(new ArrayList<>());
        return electricEngine;
    }
}
