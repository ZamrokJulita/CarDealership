package com.carDealership.carDealership.dto.client;

import com.carDealership.carDealership.models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientReadDto {
    int id;
    String address;
    String phoneNumber;
    String email;

    public ClientReadDto(Client client) {
        this.id = client.getId();
        this.address = client.getAddress();
        this.phoneNumber = client.getPhoneNumber();
        this.email = client.getEmail();
    }

}
