package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javierballon.springboot.crm.springboot_crm.entities.Client;
import com.javierballon.springboot.crm.springboot_crm.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> update(Long id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            
            Client clientBD = optionalClient.orElseThrow();

            if(client.getName() != null){
                clientBD.setName(client.getName());
            }
            if(client.getLastname() != null){
                clientBD.setLastname(client.getLastname());
            }
            if(client.getAddress() != null){
                clientBD.setAddress(client.getAddress());
            }
            if(client.getPhone() != null){
                clientBD.setPhone(client.getPhone());
            }

            return Optional.of(clientRepository.save(clientBD));
        }

        return optionalClient;
    }

    @Override
    public Optional<Client> delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        optionalClient.ifPresent(client -> {
            clientRepository.delete(client);
        });

        return optionalClient;
    }
}