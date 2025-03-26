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
public class CorporateClientReadDto extends ClientReadDto {
    String companyName;
    String nip;

    public CorporateClientReadDto(CorporateClient corporateClient) {
        super(corporateClient);
        this.companyName = corporateClient.getCompanyName();
        this.nip = corporateClient.getNip();
    }
}
