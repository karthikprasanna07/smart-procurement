package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.Vendor;
import com.procurement.smart_procurement.dto.report.VendorReportDTO;

import java.util.List;

/**
 * VendorService
 * --------------------------------------------------
 * Business logic for vendor operations.
 *
 * NOTE:
 * Existing methods are untouched.
 * Report-related method is added separately
 * for Sprint 6 (Jasper Reports).
 */
public interface VendorService {

    // Create new vendor
    Vendor createVendor(Vendor vendor);

    // Update existing vendor
    Vendor updateVendor(Long id, Vendor vendor);

    // Soft delete vendor (set status = INACTIVE)
    void deleteVendor(Long id);

    // Get vendor by ID
    Vendor getVendorById(Long id);

    // Get all vendors
    List<Vendor> getAllVendors();

    /* ============================================================
       REPORT RELATED METHOD (SPRINT 6)
       ============================================================ */

    /**
     * Fetches vendor data formatted specifically
     * for Jasper report generation.
     *
     * This method does NOT affect existing
     * vendor business logic.
     *
     * @return list of VendorReportDTO objects
     */
    List<VendorReportDTO> getVendorReportData();
}
