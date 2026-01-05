package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {

    // Sprint 5 core use case
    PurchaseOrder createPO(
            Long prId,
            Long vendorId,
            String comments,
            String terms
    );

    List<PurchaseOrder> getAllPOs();
}
