package com.javierballon.springboot.crm.springboot_crm.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.javierballon.springboot.crm.springboot_crm.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
