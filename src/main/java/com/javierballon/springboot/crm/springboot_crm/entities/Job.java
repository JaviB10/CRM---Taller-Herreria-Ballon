package com.javierballon.springboot.crm.springboot_crm.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createAt;

    @Temporal(TemporalType.DATE)
    private Date finish;
    
    @Column(name = "budget_accepted")
    private boolean budgetAccepted;

    @Temporal(TemporalType.DATE)
    @Column(name = "accepted_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date acceptedAt;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"jobs"})
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "job")
    @JsonIgnoreProperties({"job"})
    private Budget budget;

    @PrePersist
    public void prePrersist() {
        this.createAt = new Date();
        this.budgetAccepted = false;
    }

    public Job() {
    }
    
    public Job(String details, Date createAt, Date finish) {
        this.details = details;
        this.createAt = createAt;
        this.finish = finish;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Date getFinish() {
        return finish;
    }
    public void setFinish(Date finish) {
        this.finish = finish;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Budget getBudget() {
        return budget;
    }
    public void setBudget(Budget budget) {
        this.budget = budget;
    }
    public boolean isBudgetAccepted() {
        return budgetAccepted;
    }
    public void setBudgetAccepted(boolean budgetAccepted) {
        this.budgetAccepted = budgetAccepted;
    }
    public Date getAcceptedAt() {
        return acceptedAt;
    }
    public void setAcceptedAt(Date acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", details=" + details + ", createAt=" + createAt + ", finish=" + finish 
                + ", budgetAccepted=" + budgetAccepted + ", acceptedAt=" + acceptedAt + "}";
    }
}
