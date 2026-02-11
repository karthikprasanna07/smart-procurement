package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.Vendor;
import com.procurement.smart_procurement.exception.ResourceNotFoundException;
import com.procurement.smart_procurement.repository.VendorRepository;
import com.procurement.smart_procurement.service.VendorService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.procurement.smart_procurement.dto.report.VendorReportDTO;


import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private static final Logger logger =
            LoggerFactory.getLogger(VendorServiceImpl.class);

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // ================= CREATE =================
    @Override
    public Vendor createVendor(Vendor vendor) {

        logger.info("Creating vendor with name: {}", vendor.getName());

        vendor.setStatus("ACTIVE");
        return vendorRepository.save(vendor);
    }

    // ================= UPDATE =================
    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {

        logger.info("Updating vendor with id: {}", id);

        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found with id " + id)
                );

        existingVendor.setName(vendor.getName());
        existingVendor.setEmail(vendor.getEmail());
        existingVendor.setPhone(vendor.getPhone());
        existingVendor.setAddress(vendor.getAddress());
        existingVendor.setStatus(vendor.getStatus());

        return vendorRepository.save(existingVendor);
    }

    // ================= SOFT DELETE =================
    @Override
    public void deleteVendor(Long id) {

        logger.info("Soft deleting vendor with id: {}", id);

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found with id " + id)
                );

        vendor.setStatus("INACTIVE");
        vendorRepository.save(vendor);
    }

    // ================= GET BY ID =================
    @Override
    public Vendor getVendorById(Long id) {

        logger.info("Fetching vendor with id: {}", id);

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found with id " + id)
                );



        return vendor;
    }

    // ================= GET ALL =================
    @Override
    public List<Vendor> getAllVendors() {

        logger.info("Fetching all vendors");
        return vendorRepository.findAll();
    }
        /* ============================================================
       REPORT RELATED METHOD (SPRINT 6)
       ============================================================ */

    /**
     * Prepares vendor data specifically for JasperReports.
     *
     * IMPORTANT:
     * - This method does NOT change any existing vendor logic
     * - It only reads data from database
     * - It converts Entity objects into Report DTOs
     *
     * @return List of VendorReportDTO for report generation
     */
    @Override
    public List<VendorReportDTO> getVendorReportData() {

        logger.info("Fetching vendor data for Vendor Report");

        // Step 1: Fetch all vendors from database
        List<Vendor> vendorList = vendorRepository.findAll();

        // Step 2: Convert Vendor entities into VendorReportDTOs
        // Stream API is used for clean and readable transformation
        return vendorList.stream()

                // Map each Vendor entity to VendorReportDTO
                .map(vendor -> new VendorReportDTO(
                        vendor.getId(),       // Vendor unique ID
                        vendor.getName(),     // Vendor name
                        vendor.getEmail(),    // Vendor email
                        vendor.getPhone(),    // Vendor phone number
                        vendor.getStatus()    // Vendor status
                ))

                // Collect mapped objects into a List
                .toList();
    }

}
