package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.service.PurchaseRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prs")   // Base URL for Purchase Request APIs
public class PurchaseRequestController {

    // Service layer object (contains PR business logic)
    private final PurchaseRequestService prService;

    // Inject PurchaseRequestService
    public PurchaseRequestController(PurchaseRequestService prService) {
        this.prService = prService;
    }

    // ---------- CREATE PR ----------
    // API: POST /api/prs
    // Creates a new Purchase Request
    @PostMapping
    public PurchaseRequest createPR(@RequestBody PurchaseRequest pr) {
        return prService.createPR(pr);
    }

    // ---------- GET ALL PRs ----------
    // API: GET /api/prs
    // Fetches all Purchase Requests from DB
    @GetMapping
    public List<PurchaseRequest> getAllPRs() {
        return prService.getAllPRs();
    }

    // ---------- UPDATE PR ----------
    // API: PUT /api/prs/{id}
    // Updates an existing Purchase Request
    @PutMapping("/{id}")
    public PurchaseRequest updatePR(
            @PathVariable Long id,
            @RequestBody PurchaseRequest pr) {

        // Calling implementation method for update logic
        return ((com.procurement.smart_procurement.service.impl.PurchaseRequestServiceImpl) prService)
                .updatePR(id, pr);
    }
}
