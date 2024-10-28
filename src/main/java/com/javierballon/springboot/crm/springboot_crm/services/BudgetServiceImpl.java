package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javierballon.springboot.crm.springboot_crm.entities.Budget;
import com.javierballon.springboot.crm.springboot_crm.entities.Job;
import com.javierballon.springboot.crm.springboot_crm.repositories.BudgetRepository;
import com.javierballon.springboot.crm.springboot_crm.repositories.JobRepository;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Budget> findAll() {
        return (List<Budget>) budgetRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> findById(Long id) {
        return budgetRepository.findById(id);
    }

    @Override
    @Transactional
    public Budget save(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()) {
            Job job = optionalJob.get();
            Budget budget = new Budget();
            budget.setJob(job);
            return budgetRepository.save(budget);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }
    }

    @Override
    @Transactional
    public Optional<Budget> delete(Long id) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        optionalBudget.ifPresent(budget -> {
            budgetRepository.delete(budget);
        });
        return optionalBudget;
    }
}
