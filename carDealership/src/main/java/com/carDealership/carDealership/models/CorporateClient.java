package com.carDealership.carDealership.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="klienci_firmowi")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorporateClient extends Client {
    @Column(name="nazwa_firmy", unique=true)
    String companyName;

    @Column(name="nip", unique=true, length=10)
    String nip;
}
