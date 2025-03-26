package com.carDealership.carDealership.services;

import com.carDealership.carDealership.dto.client.*;
import com.carDealership.carDealership.models.Client;
import com.carDealership.carDealership.models.CorporateClient;
import com.carDealership.carDealership.models.IndividualClient;
import com.carDealership.carDealership.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ClientService(ClientRepository clientRepository, EntityManager entityManager) {
        this.clientRepository = clientRepository;
        this.entityManager = entityManager;
    }
    public List<ClientReadDto> getAll() {
        return this.clientRepository
                .findAll()
                .stream()
                .map(ClientReadDto::new)
                .collect(Collectors.toList());
    }

    public List<IndividualClientReadDto> getAllIndividual() {
        return this.clientRepository
                .findAll()
                .stream()
                .filter(client -> client instanceof IndividualClient)
                .map(client -> (IndividualClient)client)
                .map(IndividualClientReadDto::new)
                .collect(Collectors.toList());
    }

    public List<CorporateClientReadDto> getAllCorporate() {
        return this.clientRepository
                .findAll()
                .stream()
                .filter(client -> client instanceof CorporateClient)
                .map(client -> (CorporateClient)client)
                .map(CorporateClientReadDto::new)
                .collect(Collectors.toList());
    }

    public ClientReadDto save(ClientCreateDto clientCreateDto) {
        var result = clientRepository.save(clientCreateDto.convertToClient());
        return new ClientReadDto(result);
    }

    public void delete(int idClient){
        if(this.clientRepository.existsById(idClient)){
            this.clientRepository.deleteById(idClient);
            return;
        } throw new IllegalArgumentException();
    }

    @Transactional
    public ClientReadDto updateClient(ClientUpdateDto clientUpdateDto) {
        try{
            Client updatedClient = this.entityManager.merge(clientUpdateDto.convertToClient());
            return new ClientReadDto(updatedClient);
        } catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }

}
