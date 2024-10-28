package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import com.javierballon.springboot.crm.springboot_crm.entities.User;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> delete(Long id);

    Boolean existsByUsername(String username);
}
