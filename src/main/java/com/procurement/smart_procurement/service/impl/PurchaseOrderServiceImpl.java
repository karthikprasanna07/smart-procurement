package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.PurchaseOrder;
import com.procurement.smart_procurement.repository.PurchaseOrderRepository;
import com.procurement.smart_procurement.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public PurchaseOrder createPO(PurchaseOrder po) {
        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getAllPOs() {
        return poRepository.findAll();
    }
}
