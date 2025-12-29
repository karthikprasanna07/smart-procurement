package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.repository.PurchaseRequestRepository;
import com.procurement.smart_procurement.service.PurchaseRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

    private final PurchaseRequestRepository prRepository;

    public PurchaseRequestServiceImpl(PurchaseRequestRepository prRepository) {
        this.prRepository = prRepository;
    }

    // 🔢 Auto-generate PR number
    private String generatePRNumber(Long id) {
        return "PR-" + Year.now() + "-" + String.format("%04d", id);
    }

    @Override
    public PurchaseRequest createPR(PurchaseRequest pr) {

        // 1️⃣ Budget validation
        if (pr.getBudget() == null || pr.getBudget() > 100000) {
            throw new IllegalArgumentException("Budget exceeds allowed limit");
        }

        // 2️⃣ Set default values
        pr.setStatus("DRAFT");
        pr.setRequestedDate(LocalDateTime.now());

        // 3️⃣ Save first time to get ID
        PurchaseRequest saved = prRepository.save(pr);

        // 4️⃣ Generate PR number
        saved.setRequestNumber(generatePRNumber(saved.getId()));

        // 5️⃣ Save again with PR number
        return prRepository.save(saved);
    }

    @Override
    public List<PurchaseRequest> getAllPRs() {
        return prRepository.findAll();
    }

    // 🔄 Update PR (Sprint 4)
    public PurchaseRequest updatePR(Long id, PurchaseRequest updatedPr) {

        PurchaseRequest existing = prRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Request not found"));

        if (updatedPr.getBudget() == null || updatedPr.getBudget() > 100000) {
            throw new IllegalArgumentException("Budget exceeds allowed limit");
        }

        existing.setItemDescription(updatedPr.getItemDescription());
        existing.setQuantity(updatedPr.getQuantity());
        existing.setBudget(updatedPr.getBudget());
        existing.setStatus(updatedPr.getStatus());

        return prRepository.save(existing);
    }
}
