package com.javierballon.springboot.crm.springboot_crm.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "job_id")
    @JsonIgnoreProperties({"budget"})
    private Job job;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    @JsonIgnoreProperties({"budget"})
    private List<Material> materials;

    public Budget() {
        this.materials = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
    public List<Material> getMaterials() {
        return materials;
    }
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", materials=" + materials + "}";
    } 
}
