package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.entity.PurchaseOrder;
import com.procurement.smart_procurement.service.PurchaseOrderService;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PermitAll   // 🔑 THIS IS THE FIX
@RestController
@RequestMapping("/api/v1/pos")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    public PurchaseOrderController(PurchaseOrderService poService) {
        this.poService = poService;
    }

    // Sprint 5: Create PO from APPROVED PR
    @PostMapping("/create")
    public PurchaseOrder createPO(
            @RequestParam Long prId,
            @RequestParam Long vendorId,
            @RequestParam(required = false) String comments,
            @RequestParam(required = false) String terms) {

        return poService.createPO(prId, vendorId, comments, terms);
    }

    // Existing functionality (UNCHANGED)
    @GetMapping
    public List<PurchaseOrder> getAllPOs() {
        return poService.getAllPOs();
    }
}
