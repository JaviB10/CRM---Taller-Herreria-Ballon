package com.javierballon.springboot.crm.springboot_crm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.javierballon.springboot.crm.springboot_crm.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
