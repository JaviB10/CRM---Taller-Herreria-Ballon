package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.List;
import java.util.Optional;

import com.javierballon.springboot.crm.springboot_crm.entities.Job;

public interface JobService {

    List<Job> findAll();

    Optional<Job> findById(Long id);

    Job save(Job job, Long id);

    Optional<Job> update(Long id, Job job);

    Optional<Job> delete(Long id);
}
