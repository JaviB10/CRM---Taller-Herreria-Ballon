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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javierballon.springboot.crm.springboot_crm.entities.Material;
import com.javierballon.springboot.crm.springboot_crm.services.MaterialService;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/")
    public List<Material> list() {
        return materialService.findAll();
    } 

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Material> optionalMaterial = materialService.findById(id);
        if (optionalMaterial.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalMaterial.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody Material material, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.save(material, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Material material, @PathVariable Long id) {
        Optional<Material> optionalMaterial = materialService.update(id, material);
        if (optionalMaterial.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalMaterial.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Material> optionalMaterial = materialService.delete(id);
        if (optionalMaterial.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalMaterial.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
