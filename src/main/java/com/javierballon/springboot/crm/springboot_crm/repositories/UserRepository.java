package com.javierballon.springboot.crm.springboot_crm.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.javierballon.springboot.crm.springboot_crm.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
