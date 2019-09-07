package com.csse.procurement.CSSE_WE_29.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, ObjectId> {
	public List<PurchaseOrder> findAllByOrdStatus();
	public PurchaseOrder findByOrdId(String orderId);
	public PurchaseOrder findTop1ByOrderByOrdIdDesc();
}
