package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.service.PurchaseRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prs")
public class PurchaseRequestController {

    private final PurchaseRequestService prService;

    public PurchaseRequestController(PurchaseRequestService prService) {
        this.prService = prService;
    }

    @PostMapping
    public PurchaseRequest createPR(@RequestBody PurchaseRequest pr) {
        return prService.createPR(pr);
    }

    @GetMapping
    public List<PurchaseRequest> getAllPRs() {
        return prService.getAllPRs();
    }
}
