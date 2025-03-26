package com.carDealership.carDealership.models;

import com.carDealership.carDealership.enums.EngineFuelType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="silniki")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="moc_silnika")
    int power;

    @Column(name="rodzaj_paliwa")
    @Enumerated(EnumType.STRING)
    EngineFuelType fuelType;

    @ManyToMany(mappedBy = "engines", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Car> cars;

}
