package com.carDealership.carDealership.dto.client;

import com.carDealership.carDealership.models.Client;
import com.carDealership.carDealership.models.IndividualClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IndividualClientReadDto extends ClientReadDto {

    String name;
    String surname;
    String pesel;

    public IndividualClientReadDto(IndividualClient individualClient) {
        super(individualClient);
        this.name = individualClient.getName();
        this.surname = individualClient.getSurname();
        this.pesel = individualClient.getPesel();
    }
}
