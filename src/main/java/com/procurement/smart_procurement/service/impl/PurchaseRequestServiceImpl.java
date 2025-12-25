package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.repository.PurchaseRequestRepository;
import com.procurement.smart_procurement.service.PurchaseRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

    private final PurchaseRequestRepository prRepository;

    public PurchaseRequestServiceImpl(PurchaseRequestRepository prRepository) {
        this.prRepository = prRepository;
    }

    @Override
    public PurchaseRequest createPR(PurchaseRequest pr) {
        return prRepository.save(pr);
    }

    @Override
    public List<PurchaseRequest> getAllPRs() {
        return prRepository.findAll();
    }
}
