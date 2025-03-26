package com.carDealership.carDealership.services;

import com.carDealership.carDealership.dto.transaction.TransactionCreateDto;
import com.carDealership.carDealership.dto.transaction.TransactionReadDto;
import com.carDealership.carDealership.dto.transaction.TransactionUpdateDto;
import com.carDealership.carDealership.models.Car;
import com.carDealership.carDealership.models.Client;
import com.carDealership.carDealership.models.Transaction;
import com.carDealership.carDealership.repositories.CarRepository;
import com.carDealership.carDealership.repositories.ClientRepository;
import com.carDealership.carDealership.repositories.TransactionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;
    CarRepository carRepository;
    ClientRepository clientRepository;
    EntityManager entityManager;

    public TransactionService(TransactionRepository transactionRepository, CarRepository carRepository, ClientRepository clientRepository, EntityManager entityManager) {
        this.transactionRepository = transactionRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.entityManager = entityManager;
    }

    public List<TransactionReadDto> getAll() {
        return this.transactionRepository.findAll().stream().map(TransactionReadDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Optional<TransactionReadDto> save(TransactionCreateDto transactionCreateDto) {
        Optional<Car> carOpt = this.carRepository.findById(transactionCreateDto.getCarId());

        if (carOpt.isEmpty()) return Optional.empty();

        Optional<Client> clientOpt = this.clientRepository.findById(transactionCreateDto.getClientId());

        if (clientOpt.isEmpty()) return Optional.empty();

        Car car = carOpt.get();
        Client client = clientOpt.get();
        Transaction transaction = transactionCreateDto.convertToTransaction();
        transaction.setCar(car);
        transaction.setClient(client);

        try {
            var result = this.transactionRepository.save(transaction);
            return Optional.of(new TransactionReadDto(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Transactional
    public void delete(int transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        // Break relationship from Car side (if any)
        transaction.setCar(null);

        // Break relationship from Client side
        Client client = transaction.getClient();
        if (client != null) {
            client.getTransactions().remove(transaction); // break the collection reference
            transaction.setClient(null);
        }

        entityManager.remove(entityManager.contains(transaction)
                ? transaction
                : entityManager.merge(transaction));

        entityManager.flush(); // Force immediate delete
    }

    @Transactional
    public Optional<TransactionReadDto> update(TransactionUpdateDto transactionUpdateDto) {
        try{
            var transactionOpt = this.transactionRepository.findById(transactionUpdateDto.getId());
            if (transactionOpt.isEmpty()) return Optional.empty();
            Transaction transaction = transactionOpt.get();

            Hibernate.initialize(transaction.getClient());
            Hibernate.initialize(transaction.getCar());

            Transaction updatedTransaction = transactionUpdateDto.convertToTransaction();
            updatedTransaction.setClient(transaction.getClient());
            updatedTransaction.setCar(transaction.getCar());
            if(transaction.getClient().getId() != transactionUpdateDto.getClientId()){
                var clientOpt = this.clientRepository.findById(updatedTransaction.getClient().getId());
                if (clientOpt.isEmpty()) return Optional.empty();
                updatedTransaction.setClient(clientOpt.get());
            }

            if(transaction.getCar().getId() != transactionUpdateDto.getCarId()){
                var carOpt = this.carRepository.findById(updatedTransaction.getCar().getId());
                if (carOpt.isEmpty()) return Optional.empty();
                updatedTransaction.setCar(carOpt.get());
            }

            var result = this.entityManager.merge(updatedTransaction);
            return Optional.of(new TransactionReadDto(result));
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
