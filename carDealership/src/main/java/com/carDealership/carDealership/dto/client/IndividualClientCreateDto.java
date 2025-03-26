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
public class IndividualClientCreateDto extends ClientCreateDto {
    String name;
    String surname;
    String pesel;


    @Override
    public IndividualClient convertToClient() {
        IndividualClient individualClient = new IndividualClient();
        individualClient.setName(name);
        individualClient.setSurname(surname);
        individualClient.setPesel(pesel);
        individualClient.setEmail(email);
        individualClient.setAddress(address);
        individualClient.setPhoneNumber(phoneNumber);
        return individualClient;
    }
}
