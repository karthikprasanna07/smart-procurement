package com.procurement.smart_procurement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * PurchaseRequest Entity
 * ----------------------
 * Represents a Purchase Request (PR) raised by a user.
 * This entity is mapped to the 'purchase_requests' table.
 */
@Entity
@Table(name = "purchase_requests")
public class PurchaseRequest {

    /**
     * Primary key (Auto-incremented)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Auto-generated Purchase Request Number
     * Example: PR-2026-0003
     */
    @Column(unique = true)
    private String requestNumber;

    /**
     * Description of the item being requested
     */
    @NotBlank(message = "Item description is required")
    @Column(nullable = false)
    private String itemDescription;

    /**
     * Quantity of items requested
     * Must be at least 1
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Total budget for the purchase request
     * Must be greater than 0
     */
    @NotNull(message = "Budget is required")
    @Min(value = 1, message = "Budget must be greater than 0")
    @Column(nullable = false)
    private Double budget;

    /**
     * Status of the PR
     * Examples: DRAFT, APPROVED, REJECTED
     */
    @Column(nullable = false)
    private String status;

    /**
     * Date and time when PR was created
     */
    private LocalDateTime requestedDate;

    // ---------- Constructors ----------

    public PurchaseRequest() {
        // Default constructor required by JPA
    }

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
