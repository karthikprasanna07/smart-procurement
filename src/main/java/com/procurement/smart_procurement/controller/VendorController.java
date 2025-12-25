package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.dto.VendorDTO;
import com.procurement.smart_procurement.entity.Vendor;
import com.procurement.smart_procurement.mapper.VendorMapper;
import com.procurement.smart_procurement.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // ================= CREATE =================
    @PostMapping
    public Vendor createVendor(@Valid @RequestBody VendorDTO vendorDTO) {

        Vendor vendor = VendorMapper.toEntity(vendorDTO);
        return vendorService.createVendor(vendor);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public Vendor updateVendor(
            @PathVariable Long id,
            @Valid @RequestBody VendorDTO vendorDTO
    ) {
        Vendor vendor = VendorMapper.toEntity(vendorDTO);
        return vendorService.updateVendor(id, vendor);
    }

    // ================= SOFT DELETE =================
    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }
}
