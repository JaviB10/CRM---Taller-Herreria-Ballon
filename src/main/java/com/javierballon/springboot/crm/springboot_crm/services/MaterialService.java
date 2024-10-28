package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import com.javierballon.springboot.crm.springboot_crm.entities.Material;

public interface MaterialService {

    List<Material> findAll();

    Optional<Material> findById(Long id);

    Material save(Material material, Long id);

    Optional<Material> update(Long id, Material material);

    Optional<Material> delete(Long id);
}
