package com.carDealership.carDealership.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="silniki_elektryczne")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElectricEngine extends Engine {

    @Column(name="pojemnosc_akumulatora")
    double capacity;

}
