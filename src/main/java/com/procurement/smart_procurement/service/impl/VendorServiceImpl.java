package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.Vendor;
import com.procurement.smart_procurement.exception.ResourceNotFoundException;
import com.procurement.smart_procurement.repository.VendorRepository;
import com.procurement.smart_procurement.service.VendorService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
