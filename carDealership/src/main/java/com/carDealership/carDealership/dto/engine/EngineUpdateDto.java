package com.carDealership.carDealership.dto.engine;

import com.carDealership.carDealership.models.Engine;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type") // Klucz w JSON określający typ
@JsonSubTypes({
        @JsonSubTypes.Type(value = ElectricEngineUpdateDto.class, name = "electric"),
        @JsonSubTypes.Type(value = CombustionEngineUpdateDto.class, name = "combustion")
})
public abstract class EngineUpdateDto {
    Integer id;
    int power;
    String fuelType;

    public abstract Engine convertToEngine();
}
