package com.procurement.smart_procurement.mapper;

import com.procurement.smart_procurement.dto.VendorDTO;
import com.procurement.smart_procurement.entity.Vendor;

/**
 * VendorMapper
 * Converts VendorDTO <-> Vendor entity
 */
public class VendorMapper {

    // DTO → Entity
    public static Vendor toEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setEmail(dto.getEmail());
        vendor.setPhone(dto.getPhone());
        vendor.setAddress(dto.getAddress());
        vendor.setGstNumber(dto.getGstNumber());
        vendor.setRating(dto.getRating());
        vendor.setStatus(dto.getStatus());
        return vendor;
    }

    // Entity → DTO
    public static VendorDTO toDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setName(vendor.getName());
        dto.setEmail(vendor.getEmail());
        dto.setPhone(vendor.getPhone());
        dto.setAddress(vendor.getAddress());
        dto.setGstNumber(vendor.getGstNumber());
        dto.setRating(vendor.getRating());
        dto.setStatus(vendor.getStatus());
        return dto;
    }
}
