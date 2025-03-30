package com.carDealership.carDealership.dto.client;

import com.carDealership.carDealership.models.CorporateClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorporateClientUpdateDto extends ClientUpdateDto {
    Integer id;
    String companyName;
    String nip;

    public CorporateClient convertToClient() {
        CorporateClient corporateClient = new CorporateClient();
        corporateClient.setId(id);
        corporateClient.setCompanyName(companyName);
        corporateClient.setNip(nip);
        corporateClient.setEmail(email);
        corporateClient.setAddress(address);
        corporateClient.setPhoneNumber(phoneNumber);
        return corporateClient;
    }
}
