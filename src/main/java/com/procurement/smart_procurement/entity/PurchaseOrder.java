package com.procurement.smart_procurement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String poNumber;

    private Double totalAmount;

    private String status; // CREATED / SENT / CLOSED

    private LocalDate createdDate;

    // 🔗 Link to approved Purchase Request
    @ManyToOne
    @JoinColumn(name = "pr_id", nullable = false)
    private PurchaseRequest purchaseRequest;

    // 🔗 Vendor validation
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    // 📝 Sprint 5 additions
    @Column(length = 500)
    private String comments;

    @Column(length = 500)
    private String terms;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Long id, String poNumber, Double totalAmount, String status,
                         LocalDate createdDate, PurchaseRequest purchaseRequest,
                         Vendor vendor, String comments, String terms) {
        this.id = id;
        this.poNumber = poNumber;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdDate = createdDate;
        this.purchaseRequest = purchaseRequest;
        this.vendor = vendor;
        this.comments = comments;
        this.terms = terms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
