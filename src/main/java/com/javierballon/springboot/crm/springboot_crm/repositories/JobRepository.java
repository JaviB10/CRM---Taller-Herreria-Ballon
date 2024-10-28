package com.javierballon.springboot.crm.springboot_crm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.javierballon.springboot.crm.springboot_crm.entities.Job;

public interface JobRepository extends CrudRepository<Job, Long> {

}
