package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;

@Document(collection = "Item")
@Component
public class Item {
	
	@Id
	private String Id;
	private int itemId;
	private double itemPrice;
	private String itemType;
	private int itemQuantity;
	private String itemMetric;
	private String supplierId;
	
	

	

	public Item(String id, int itemId, double itemPrice, String itemType, int itemQuantity, String itemMetric,
			String supplierId) {
		super();
		Id = id;
		this.itemId = itemId;
		this.itemPrice = itemPrice;
		this.itemType = itemType;
		this.itemQuantity = itemQuantity;
		this.itemMetric = itemMetric;
		this.supplierId = supplierId;
	}

	

	public Item(int itemId, double itemPrice, String itemType, int itemQuantity, String itemMetric, String supplierId) {
		super();
		this.itemId = itemId;
		this.itemPrice = itemPrice;
		this.itemType = itemType;
		this.itemQuantity = itemQuantity;
		this.itemMetric = itemMetric;
		this.supplierId = supplierId;
	}

	public Item(double itemPrice, String itemType, int itemQuantity, String itemMetric, String supplierId) {
		super();
		this.itemPrice = itemPrice;
		this.itemType = itemType;
		this.itemQuantity = itemQuantity;
		this.itemMetric = itemMetric;
		this.supplierId = supplierId;
	}


	public Item(int itemId, int itemQuantity) {
		super();
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}



	public Item() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}


	public String getItemMetric() {
		return itemMetric;
	}


	public void setItemMetric(String itemMetric) {
		this.itemMetric = itemMetric;
	}
	
	
	
}
