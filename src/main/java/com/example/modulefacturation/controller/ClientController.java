package com.example.modulefacturation.controller;

import com.example.modulefacturation.dto.ClientDTO;
import com.example.modulefacturation.model.Client;
import com.example.modulefacturation.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients().stream()
                .map(client -> new ClientDTO(
                        client.getId(),
                        client.getName(),
                        client.getEmail(),
                        client.getSiret(),
                        client.getCreationDate()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> ResponseEntity.ok(new ClientDTO(
                        client.getId(),
                        client.getName(),
                        client.getEmail(),
                        client.getSiret(),
                        client.getCreationDate()
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody Client client) {
        Client savedClient = clientService.createClient(client);
        return new ClientDTO(
                savedClient.getId(),
                savedClient.getName(),
                savedClient.getEmail(),
                savedClient.getSiret(),
                savedClient.getCreationDate()
        );
    }
}