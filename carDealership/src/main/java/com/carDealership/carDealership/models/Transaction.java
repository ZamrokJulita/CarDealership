package com.carDealership.carDealership.models;

import com.carDealership.carDealership.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transakcje")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="data_transakcji")
    LocalDateTime transactionDate;

    @Column(name="kwota")
    BigDecimal totalAmount;

    @Column(name="sposob_platnosci")
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @Column(name="czy_zarejestrowany")
    boolean registered;

    @Column(name="czy_ubezpieczony")
    boolean insured;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="car_id")
    Car car;

}
