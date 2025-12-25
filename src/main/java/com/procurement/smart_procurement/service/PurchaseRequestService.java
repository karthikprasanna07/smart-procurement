package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.PurchaseRequest;

import java.util.List;

public interface PurchaseRequestService {
    PurchaseRequest createPR(PurchaseRequest pr);
    List<PurchaseRequest> getAllPRs();
}
