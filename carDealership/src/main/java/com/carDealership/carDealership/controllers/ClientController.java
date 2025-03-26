package com.carDealership.carDealership.controllers;

import com.carDealership.carDealership.dto.client.*;
import com.carDealership.carDealership.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientReadDto>> getAllClients() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/individual")
    public ResponseEntity<List<IndividualClientReadDto>> getAllIndividualClients() {
        return new ResponseEntity<>(clientService.getAllIndividual(), HttpStatus.OK);
    }

    @GetMapping("/corporate")
    public ResponseEntity<List<CorporateClientReadDto>> getAllCorporateClients() {
        return new ResponseEntity<>(clientService.getAllCorporate(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientReadDto> createClient(@RequestBody ClientCreateDto clientCreateDto) {
        ClientReadDto clientReadDto = clientService.save(clientCreateDto);
        return new ResponseEntity<>(clientReadDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable int clientId) {
        clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClientReadDto> updateClient(@RequestBody ClientUpdateDto clientUpdateDto) {
        ClientReadDto clientReadDto = clientService.updateClient(clientUpdateDto);
        return new ResponseEntity<>(clientReadDto, HttpStatus.OK);
    }


}
