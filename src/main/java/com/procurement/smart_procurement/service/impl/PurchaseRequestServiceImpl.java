package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.PurchaseRequest;
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
 * Handles PR creation, update, auto-number generation, and validations.
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
     * Example: PR-2026-0003
     */
    private String generatePRNumber(Long id) {
        return "PR-" + Year.now() + "-" + String.format("%04d", id);
    }

    /**
     * CREATE Purchase Request
     * -----------------------
     * - Validates budget limit
     * - Sets default status and requested date
     * - Auto-generates PR number
     */
    @Override
    public PurchaseRequest createPR(PurchaseRequest pr) {

        // 🔒 Business validation: budget upper limit
        if (pr.getBudget() == null || pr.getBudget() > 100000) {
            throw new IllegalArgumentException("Budget exceeds allowed limit");
        }

        // 📌 Set default PR status
        pr.setStatus("DRAFT");

        // 📌 Set PR creation timestamp
        pr.setRequestedDate(LocalDateTime.now());

        // 💾 Save once to generate database ID
        PurchaseRequest savedPR = prRepository.save(pr);

        // 🔢 Generate PR number using generated ID
        savedPR.setRequestNumber(generatePRNumber(savedPR.getId()));

        // 💾 Save again with PR number
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
     * UPDATE Purchase Request
     * -----------------------
     * Updates item description, quantity, budget, and status.
     */
    @Override
    public PurchaseRequest updatePR(Long id, PurchaseRequest updatedPr) {

        // 🔍 Fetch existing PR or throw error
        PurchaseRequest existingPR = prRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Request not found"));

        // 🔒 Business validation: budget upper limit
        if (updatedPr.getBudget() == null || updatedPr.getBudget() > 100000) {
            throw new IllegalArgumentException("Budget exceeds allowed limit");
        }

        // ✏️ Update allowed fields
        existingPR.setItemDescription(updatedPr.getItemDescription());
        existingPR.setQuantity(updatedPr.getQuantity());
        existingPR.setBudget(updatedPr.getBudget());
        existingPR.setStatus(updatedPr.getStatus());

        // 💾 Save updated PR
        return prRepository.save(existingPR);
    }
}
