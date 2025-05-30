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
public class TransactionCreateDto {

    BigDecimal totalAmount;

    String paymentMethod;

    boolean registered;

    boolean insured;

    int clientId;

    int carId;

    public Transaction convertToTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTotalAmount(this.totalAmount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPaymentMethod(PaymentMethod.fromDisplayName(this.paymentMethod));
        transaction.setRegistered(this.registered);
        transaction.setInsured(this.insured);
        return transaction;
    }
}
