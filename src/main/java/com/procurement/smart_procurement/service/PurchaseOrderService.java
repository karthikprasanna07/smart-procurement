package com.procurement.smart_procurement.service;

import com.procurement.smart_procurement.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder createPO(PurchaseOrder po);
    List<PurchaseOrder> getAllPOs();
}
