package com.carDealership.carDealership.controllers;

import com.carDealership.carDealership.dto.transaction.TransactionCreateDto;
import com.carDealership.carDealership.dto.transaction.TransactionReadDto;
import com.carDealership.carDealership.dto.transaction.TransactionUpdateDto;
import com.carDealership.carDealership.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionReadDto>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionReadDto> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        System.out.println("mega");
        var result = transactionService.save(transactionCreateDto);
        return result
                .map(transactionReadDto -> new ResponseEntity<>(transactionReadDto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TransactionReadDto> updateTransaction(@RequestBody TransactionUpdateDto transactionUpdateDto) {
        var result = transactionService.update(transactionUpdateDto);
        return result
                .map(transactionReadDto -> new ResponseEntity<>(transactionReadDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

}
