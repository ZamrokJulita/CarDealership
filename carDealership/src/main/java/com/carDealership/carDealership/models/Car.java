package com.carDealership.carDealership.models;

import com.carDealership.carDealership.enums.CarBodyType;
import com.carDealership.carDealership.enums.CarDrivetrainType;
import com.carDealership.carDealership.enums.CarModel;
import com.carDealership.carDealership.enums.CarTransmission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="samochody")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="nazwa")
    String name;

    @Column(name="model")
    @Enumerated(EnumType.STRING)
    CarModel model;

    @Column(name="kolor")
    String color;

    @Column(name="przyspieszenie")
    double acceleration;

    @Column(name="skrzynia_biegow")
    @Enumerated(EnumType.STRING)
    CarTransmission transmission;

    @Column(name="predkosc_maksymalna")
    double topSpeed;

    @Column(name="zuzycie_paliwa")
    double gasMileage;

    @Column(name="rodzaj_napedu")
    @Enumerated(EnumType.STRING)
    CarDrivetrainType drivetrainType;

    @Column(name="opis")
    String description;

    @Column(name="rodzaj_nadwozia")
    @Enumerated(EnumType.STRING)
    CarBodyType bodyType;

    @Column(name="cena", precision = 10, scale = 2)
    BigDecimal price;

    @Column(name="zdjecie")
    String imagePath;

    @Column(name="numer_vin", unique = true)
    String vinNumber;

    @Column(name="rok_produkcji")
    int productionYear;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="samochod_silnik", joinColumns = @JoinColumn(name="car_id"), inverseJoinColumns = @JoinColumn(name="engine_id"))
    List<Engine> engines = new ArrayList<>();
}
