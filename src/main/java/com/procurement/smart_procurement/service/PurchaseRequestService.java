package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.PurchaseRequest;

import java.util.List;

/**
 * PurchaseRequestService
 * ----------------------
 * Defines business operations for Purchase Requests.
 */
public interface PurchaseRequestService {

    PurchaseRequest createPR(PurchaseRequest pr);

    PurchaseRequest updatePR(Long id, PurchaseRequest pr);

    List<PurchaseRequest> getAllPRs();

    // ✅ ADD THIS
    PurchaseRequest getPRById(Long id);
}
