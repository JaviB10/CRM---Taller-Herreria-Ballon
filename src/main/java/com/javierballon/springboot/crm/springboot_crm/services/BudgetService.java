package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import com.javierballon.springboot.crm.springboot_crm.entities.Budget;

public interface BudgetService {

    List<Budget> findAll();

    Optional<Budget> findById(Long id);

    Budget save(Long id);

    Optional<Budget> delete(Long id);
}
