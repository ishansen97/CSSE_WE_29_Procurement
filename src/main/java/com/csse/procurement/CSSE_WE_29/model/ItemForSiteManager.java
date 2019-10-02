package com.csse.procurement.CSSE_WE_29.model;

import org.springframework.stereotype.Component;

@Component
public class ItemForSiteManager {

	private int itemId;
	private double itemPrice;
	private String itemType;
	private int itemQuantity;
	private String itemMetric;
	private String supplierId;
	private String supplierName;
	private boolean isCritical;
	
	
	public ItemForSiteManager() {
		super();
	}
	
	public ItemForSiteManager(int itemId, double itemPrice, String itemType, int itemQuantity, String itemMetric,
			String supplierId, String supplierName, boolean isCritical) {
		super();
		this.itemId = itemId;
		this.itemPrice = itemPrice;
		this.itemType = itemType;
		this.itemQuantity = itemQuantity;
		this.itemMetric = itemMetric;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.isCritical = isCritical;
	}
	public int getItemId() {
		return itemId;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public String getItemType() {
		return itemType;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public String getItemMetric() {
		return itemMetric;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public boolean isCritical() {
		return isCritical;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public void setItemMetric(String itemMetric) {
		this.itemMetric = itemMetric;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public void setCritical(boolean isCritical) {
		this.isCritical = isCritical;
	}



}
