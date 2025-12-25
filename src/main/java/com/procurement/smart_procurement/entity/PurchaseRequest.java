package com.procurement.smart_procurement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_requests")
public class PurchaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestNumber;
    private String description;
    private Double amount;
    private String status; // DRAFT / APPROVED / REJECTED
    private LocalDate requestedDate;

    public PurchaseRequest() {
    }

    public PurchaseRequest(Long id, String requestNumber, String description,
                           Double amount, String status, LocalDate requestedDate) {
        this.id = id;
        this.requestNumber = requestNumber;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.requestedDate = requestedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }
}
