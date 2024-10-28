package com.javierballon.springboot.crm.springboot_crm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javierballon.springboot.crm.springboot_crm.entities.Client;
import com.javierballon.springboot.crm.springboot_crm.services.ClientService;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public List<Client> list() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.findById(id);

        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } 

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id) {

        Optional<Client> optionalClient = clientService.update(id, client);
        if (optionalClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalClient.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.delete(id);
        if (optionalClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalClient.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
