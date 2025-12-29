package com.procurement.smart_procurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * PurchaseRequest Entity
 * Represents a Purchase Request raised by a user
 */
@Entity
@Table(name = "purchase_requests")
public class PurchaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto-generated PR Number (PR-2024-0001)
    @Column(unique = true)
    private String requestNumber;

    // Item / product description
    @Column(nullable = false)
    private String itemDescription;

    // Quantity of items requested
    @Column(nullable = false)
    private Integer quantity;

    // Total budget amount
    @Column(nullable = false)
    private Double budget;

    // DRAFT / APPROVED / REJECTED
    @Column(nullable = false)
    private String status;

    // When PR is created
    private LocalDateTime requestedDate;

    // ---------- Constructors ----------
    public PurchaseRequest() {}

    // ---------- Getters & Setters ----------
    public Long getId() {
        return id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }
}
