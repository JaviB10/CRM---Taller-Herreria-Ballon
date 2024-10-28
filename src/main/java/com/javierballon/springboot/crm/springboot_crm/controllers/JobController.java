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

import com.javierballon.springboot.crm.springboot_crm.entities.Job;
import com.javierballon.springboot.crm.springboot_crm.services.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/")
    public List<Job> list() {
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Job> optionalJob = jobService.findById(id);
        if (optionalJob.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalJob.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody Job job, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.save(job, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Job job, @PathVariable Long id) {
        Optional<Job> optionalJob = jobService.update(id, job);
        if (optionalJob.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalJob.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Job> optionalJob = jobService.delete(id);
        if (optionalJob.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optionalJob.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
