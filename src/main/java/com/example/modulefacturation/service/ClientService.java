package com.example.modulefacturation.service;

import com.example.modulefacturation.model.Client;
import com.example.modulefacturation.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        if (client.getName() == null || client.getEmail() == null || client.getSiret() == null) {
            throw new IllegalArgumentException("Tous les champs doivent être remplis.");
        }
        client.setCreationDate(LocalDate.now());
        return clientRepository.save(client);
    }
}