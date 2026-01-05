package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.service.PurchaseRequestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PurchaseRequestController
 * -------------------------
 * Exposes REST APIs for Purchase Request operations.
 */
@RestController
@RequestMapping("/api/prs")
public class PurchaseRequestController {

    private final PurchaseRequestService prService;

    /**
     * Constructor-based dependency injection
     */
    public PurchaseRequestController(PurchaseRequestService prService) {
        this.prService = prService;
    }

    /**
     * CREATE Purchase Request
     * URL: POST /api/prs
     */
    @PostMapping
    public PurchaseRequest createPR(@Valid @RequestBody PurchaseRequest pr) {
        return prService.createPR(pr);
    }

    /**
     * GET all Purchase Requests
     * URL: GET /api/prs
     */
    @GetMapping
    public List<PurchaseRequest> getAllPRs() {
        return prService.getAllPRs();
    }

    /**
     * UPDATE Purchase Request by ID
     * URL: PUT /api/prs/{id}
     */
    @PutMapping("/{id}")
    public PurchaseRequest updatePR(
            @PathVariable Long id,
            @Valid @RequestBody PurchaseRequest pr) {

        return prService.updatePR(id, pr);
    }
}
