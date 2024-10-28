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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javierballon.springboot.crm.springboot_crm.entities.Budget;
import com.javierballon.springboot.crm.springboot_crm.services.BudgetService;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/")
    public List<Budget> list() {
        return budgetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Budget> optionalBudget = budgetService.findById(id);
        if (optionalBudget.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalBudget.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.save(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Budget> optionalBudget = budgetService.delete(id);
        if (optionalBudget.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalBudget.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
