package com.procurement.smart_procurement.controller;

import com.procurement.smart_procurement.entity.PurchaseOrder;
import com.procurement.smart_procurement.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pos")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    public PurchaseOrderController(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @PostMapping
    public PurchaseOrder createPO(@RequestBody PurchaseOrder po) {
        return poService.createPO(po);
    }

    @GetMapping
    public List<PurchaseOrder> getAllPOs() {
        return poService.getAllPOs();
    }
}
