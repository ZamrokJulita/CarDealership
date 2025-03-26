package com.carDealership.carDealership.dto.engine;

import com.carDealership.carDealership.models.CombustionEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CombustionEngineReadDto extends EngineReadDto {
    double displacement;
    int cylindersNumber;

    public CombustionEngineReadDto(CombustionEngine engine){
        super(engine);
        this.displacement = engine.getDisplacement();
        this.cylindersNumber = engine.getCylindersNumber();
    }
}
