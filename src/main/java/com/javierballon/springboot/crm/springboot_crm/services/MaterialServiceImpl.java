package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javierballon.springboot.crm.springboot_crm.entities.Budget;
import com.javierballon.springboot.crm.springboot_crm.entities.Material;
import com.javierballon.springboot.crm.springboot_crm.repositories.BudgetRepository;
import com.javierballon.springboot.crm.springboot_crm.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Material> findAll() {
        return (List<Material>) materialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    @Transactional
    public Material save(Material material, Long id) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if(optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            material.setBudget(budget);
            return materialRepository.save(material);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }
    }

    @Override
    @Transactional
    public Optional<Material> update(Long id, Material material) {
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        if (optionalMaterial.isPresent()) {
            Material materialDB = optionalMaterial.orElseThrow();

            if (material.getMaterialName() != null) {
                materialDB.setMaterialName(material.getMaterialName());
            }
            if (material.getAmount() != 0) {
                materialDB.setAmount(material.getAmount());
            }
            if (material.getPrice() != 0) {
                materialDB.setPrice(material.getPrice());
            }
            return Optional.of(materialRepository.save(materialDB));
        }
        return optionalMaterial;
    }

    @Override
    @Transactional
    public Optional<Material> delete(Long id) {
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        optionalMaterial.ifPresent(material -> {
            materialRepository.delete(material);
        });
        return optionalMaterial;
    }
}