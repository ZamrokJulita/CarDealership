package com.carDealership.carDealership.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="silniki_spalinowe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CombustionEngine extends Engine{

    @Column(name="pojmenosc")
    double displacement;

    @Column(name="liczba_cylindrow")
    int cylindersNumber;
}
