package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.entity.Supplier;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
	public Supplier findBySupplierId(String supplierId);
	public Supplier findTop1ByOrderBySupplierIdDesc();
}
