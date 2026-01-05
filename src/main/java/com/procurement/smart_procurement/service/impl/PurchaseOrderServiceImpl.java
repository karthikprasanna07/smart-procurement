package com.procurement.smart_procurement.service.impl;

import com.procurement.smart_procurement.entity.PurchaseOrder;
import com.procurement.smart_procurement.entity.PurchaseRequest;
import com.procurement.smart_procurement.entity.Vendor;
import com.procurement.smart_procurement.repository.PurchaseOrderRepository;
import com.procurement.smart_procurement.repository.PurchaseRequestRepository;
import com.procurement.smart_procurement.repository.VendorRepository;
import com.procurement.smart_procurement.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;
    private final PurchaseRequestRepository prRepository;
    private final VendorRepository vendorRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepository,
                                    PurchaseRequestRepository prRepository,
                                    VendorRepository vendorRepository) {
        this.poRepository = poRepository;
        this.prRepository = prRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public PurchaseOrder createPO(Long prId, Long vendorId, String comments, String terms) {

        // 1️⃣ Validate PR
        PurchaseRequest pr = prRepository.findById(prId)
                .orElseThrow(() -> new RuntimeException("Purchase Request not found"));

        if (!"APPROVED".equalsIgnoreCase(pr.getStatus())) {
            throw new RuntimeException("PO can be created only for APPROVED PR");
        }

        // 2️⃣ Validate Vendor
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // 3️⃣ Create PO
        PurchaseOrder po = new PurchaseOrder();
        po.setPoNumber(generatePoNumber());
        po.setPurchaseRequest(pr);
        po.setVendor(vendor);
        po.setTotalAmount(pr.getBudget()); // derived from PR
        po.setStatus("CREATED");
        po.setCreatedDate(LocalDate.now());
        po.setComments(comments);
        po.setTerms(terms);

        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getAllPOs() {
        return poRepository.findAll();
    }

    private String generatePoNumber() {
        return "PO-" + System.currentTimeMillis();
    }
}
