package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.Vendor;

import java.util.List;

/**
 * VendorService
 * Business logic for vendor operations
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
}
