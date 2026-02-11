package com.procurement.smart_procurement.dto.report;

import java.io.Serializable;

/**
 * VendorReportDTO
 * --------------------------------------------------
 * This Data Transfer Object (DTO) represents
 * vendor information specifically for report
 * generation using JasperReports.
 *
 * ✔ Used only for reporting (NOT for API requests)
 * ✔ Acts as a data carrier between Service layer
 *   and JRXML report templates
 * ✔ Follows JavaBean standards (getters required)
 */
public class VendorReportDTO implements Serializable {

    // Recommended for Serializable classes
    private static final long serialVersionUID = 1L;

    // Unique identifier of the vendor
    private Long id;

    // Official name of the vendor
    private String name;

    // Email address used for communication
    private String email;

    // Contact phone number
    private String phone;

    // Current vendor status (e.g., ACTIVE / INACTIVE)
    private String status;

    /**
     * No-argument constructor.
     * --------------------------------------------------
     * Required by frameworks and tools that
     * instantiate objects using reflection
     * (including JasperReports internally).
     */
    public VendorReportDTO() {
        // Default constructor
    }

    /**
     * Parameterized constructor.
     * --------------------------------------------------
     * Used when manually creating DTO objects
     * while mapping Entity → DTO in service layer.
     *
     * @param id     Vendor unique ID
     * @param name   Vendor name
     * @param email  Vendor email address
     * @param phone  Vendor phone number
     * @param status Vendor current status
     */
    public VendorReportDTO(
            Long id,
            String name,
            String email,
            String phone,
            String status
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    // --------------------
    // Getter methods
    // --------------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    // --------------------
    // Setter methods
    // --------------------
    // Setters are optional for reports,
    // but added for completeness and flexibility

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
