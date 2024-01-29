package com.first.redBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.redBank.model.Client;
import com.first.redBank.repository.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> showClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client registerClient(@RequestBody Client Client) {
        return clientRepository.save(Client);
    }
}
