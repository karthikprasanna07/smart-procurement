package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.exception.ResourceNotFoundException;
import com.procurement.smart_procurement.repository.PurchaseRequestRepository;
import com.procurement.smart_procurement.service.PurchaseRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

/**
 * PurchaseRequestServiceImpl
 * --------------------------
 * Implements business logic for Purchase Request operations.
 */
@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

    private final PurchaseRequestRepository prRepository;

    /**
     * Constructor-based dependency injection
     */
    public PurchaseRequestServiceImpl(PurchaseRequestRepository prRepository) {
        this.prRepository = prRepository;
    }

    /**
     * Generates a unique Purchase Request number
     * Format: PR-<YEAR>-<4 digit ID>
     */
    private String generatePRNumber(Long id) {
        return "PR-" + Year.now() + "-" + String.format("%04d", id);
    }

    /**
     * CREATE Purchase Request
     */
    @Override
    public PurchaseRequest createPR(PurchaseRequest pr) {

        if (pr.getBudget() == null || pr.getBudget() > 100000) {
            throw new IllegalArgumentException("Budget exceeds allowed limit");
        }

        pr.setStatus("DRAFT");
        pr.setRequestedDate(LocalDateTime.now());

        PurchaseRequest savedPR = prRepository.save(pr);
        savedPR.setRequestNumber(generatePRNumber(savedPR.getId()));

        return prRepository.save(savedPR);
    }

    /**
     * FETCH all Purchase Requests
     */
    @Override
    public List<PurchaseRequest> getAllPRs() {
        return prRepository.findAll();
    }

    /**
     * FETCH Purchase Request by ID
     */
    @Override
    public PurchaseRequest getPRById(Long id) {
        return prRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Purchase Request not found with id: " + id
                        ));
    }

    /**
     * UPDATE Purchase Request
     */
    @Override
    public PurchaseRequest updatePR(Long id, PurchaseRequest updatedPr) {

        PurchaseRequest existingPR = prRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Purchase Request not found with id: " + id
                        ));

        if (updatedPr.getItemDescription() != null) {
            existingPR.setItemDescription(updatedPr.getItemDescription());
        }

        if (updatedPr.getQuantity() != null) {
            existingPR.setQuantity(updatedPr.getQuantity());
        }

        if (updatedPr.getBudget() != null) {
            if (updatedPr.getBudget() > 100000) {
                throw new IllegalArgumentException("Budget exceeds allowed limit");
            }
            existingPR.setBudget(updatedPr.getBudget());
        }

        if (updatedPr.getStatus() != null) {
            existingPR.setStatus(updatedPr.getStatus());
        }

        return prRepository.save(existingPR);
    }
}
