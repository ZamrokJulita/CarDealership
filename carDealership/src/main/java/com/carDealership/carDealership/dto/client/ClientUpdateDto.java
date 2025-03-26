package com.carDealership.carDealership.dto.client;

import com.carDealership.carDealership.models.Client;
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
        @JsonSubTypes.Type(value = IndividualClientUpdateDto.class, name = "individual"),
        @JsonSubTypes.Type(value = CorporateClientUpdateDto.class, name = "corporate")
})
public abstract class ClientUpdateDto {
    Integer id;
    String address;
    String phoneNumber;
    String email;

    public abstract Client convertToClient();

}
