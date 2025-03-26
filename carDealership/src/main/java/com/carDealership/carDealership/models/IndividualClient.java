package com.carDealership.carDealership.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="klienci_prywatni")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IndividualClient extends Client{
    @Column(name="imie")
    String name;

    @Column(name="nazwisko")
    String surname;

    @Column(name="pesel", unique=true, length=11)
    String pesel;

}
