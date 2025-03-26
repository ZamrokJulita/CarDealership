package com.carDealership.carDealership.dto.transaction;

import com.carDealership.carDealership.dto.car.CarReadDto;
import com.carDealership.carDealership.dto.client.ClientReadDto;
import com.carDealership.carDealership.enums.PaymentMethod;
import com.carDealership.carDealership.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionReadDto {

    int id;

    LocalDateTime transactionDate;

    BigDecimal totalAmount;

    String paymentMethod;

    boolean registered;

    boolean insured;

    ClientReadDto client;

    CarReadDto car;

    public TransactionReadDto(Transaction transaction) {
        this.id = transaction.getId();
        this.transactionDate = transaction.getTransactionDate();
        this.totalAmount = transaction.getTotalAmount();
        this.paymentMethod = transaction.getPaymentMethod().toString();
        this.registered = transaction.isRegistered();
        this.insured = transaction.isInsured();
        this.client = new ClientReadDto(transaction.getClient());
        this.car = new CarReadDto(transaction.getCar());
    }
}
