package com.javierballon.springboot.crm.springboot_crm.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javierballon.springboot.crm.springboot_crm.entities.Client;
import com.javierballon.springboot.crm.springboot_crm.entities.Job;
import com.javierballon.springboot.crm.springboot_crm.repositories.ClientRepository;
import com.javierballon.springboot.crm.springboot_crm.repositories.JobRepository;


@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Job> findAll() {
        return (List<Job>) jobRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    @Transactional
    public Job save(Job job, Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            job.setClient(client);
            return jobRepository.save(job);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }
    }

    @Override
    @Transactional
    public Optional<Job> update(Long id, Job job) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            
            Job jobBD = optionalJob.orElseThrow();

            if(job.getDetails() != null){
                jobBD.setDetails(job.getDetails());
            }

            // Verificar si el campo budgetAccepted ha cambiado a true
            if(job.isBudgetAccepted() && !jobBD.isBudgetAccepted()) {
                jobBD.setBudgetAccepted(true);
                jobBD.setAcceptedAt(new Date()); // Establecer la fecha de aceptación
            } else if (!job.isBudgetAccepted() && jobBD.isBudgetAccepted()) {
                // Si se cambia budgetAccepted a false, podrías querer manejarlo aquí, 
                // por ejemplo, removiendo la fecha de aceptación.
                jobBD.setBudgetAccepted(false);
                jobBD.setAcceptedAt(null); // Si deseas que la fecha se elimine si se revierte la aceptación
            }

            return Optional.of(jobRepository.save(jobBD));
        }

        return optionalJob;
    }

    @Override
    @Transactional
    public Optional<Job> delete(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        optionalJob.ifPresent(job -> {
            jobRepository.delete(job);
        });

        return optionalJob;
    }
}
