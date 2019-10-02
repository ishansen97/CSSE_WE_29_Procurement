package com.csse.procurement.CSSE_WE_29.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.csse.procurement.CSSE_WE_29.entity.Item;
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.entity.Supplier;

@Component
public class PurchaseOrderResponse {

	private PurchaseOrder purchaseOrder;
	private List<Item> itemDetails;
	private Supplier supplierDetails;
	
	public PurchaseOrderResponse(PurchaseOrder purchaseOrder, List<Item> itemDetails, Supplier supplierDetails) {
		super();
		this.purchaseOrder = purchaseOrder;
		this.itemDetails = itemDetails;
		this.supplierDetails = supplierDetails;
	}

	public PurchaseOrderResponse() {
		super();
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<Item> getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(List<Item> itemDetails) {
		this.itemDetails = itemDetails;
	}

	public Supplier getSupplierDetails() {
		return supplierDetails;
	}

	public void setSupplierDetails(Supplier supplierDetails) {
		this.supplierDetails = supplierDetails;
	}
	
	
	
	
}
