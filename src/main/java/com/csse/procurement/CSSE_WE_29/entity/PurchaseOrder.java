package com.csse.procurement.CSSE_WE_29.entity;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Purchase_Order")
@Component
public class PurchaseOrder {
	
	@Id
	private String _id;
	private String ordId;
	private String ordType;
	private String ordStatus;
	private String companyName;
	private String deliveryAddress;
	private Date ordDate;
	private Date deliveryDate;
	private List<Item> itemIdList;
	private int quantity;
	private double cost;
	private String supplierId;


	public PurchaseOrder(String _id, String ordId, String ordType, String ordStatus, String companyName,
			String deliveryAddress, Date ordDate, Date deliveryDate, List<Item> itemIdList, int quantity, double cost,
			String supplierId) {
		super();
		this._id = _id;
		this.ordId = ordId;
		this.ordType = ordType;
		this.ordStatus = ordStatus;
		this.companyName = companyName;
		this.deliveryAddress = deliveryAddress;
		this.ordDate = ordDate;
		this.deliveryDate = deliveryDate;
		this.itemIdList = itemIdList;
		this.quantity = quantity;
		this.cost = cost;
		this.supplierId = supplierId;
	}


	public PurchaseOrder(String ordId, String ordType, String ordStatus, String companyName, String deliveryAddress,
			Date ordDate, Date deliveryDate, List<Item> itemIdList, int quantity, double cost, String supplierId) {
		super();
		this.ordId = ordId;
		this.ordType = ordType;
		this.ordStatus = ordStatus;
		this.companyName = companyName;
		this.deliveryAddress = deliveryAddress;
		this.ordDate = ordDate;
		this.deliveryDate = deliveryDate;
		this.itemIdList = itemIdList;
		this.quantity = quantity;
		this.cost = cost;
		this.supplierId = supplierId;
	}




	public PurchaseOrder(String ordType, String ordStatus, String companyName, String deliveryAddress, Date ordDate,
			Date deliveryDate, List<Item> itemIdList, int quantity, double cost, String supplierId) {
		super();
		this.ordType = ordType;
		this.ordStatus = ordStatus;
		this.companyName = companyName;
		this.deliveryAddress = deliveryAddress;
		this.ordDate = ordDate;
		this.deliveryDate = deliveryDate;
		this.itemIdList = itemIdList;
		this.quantity = quantity;
		this.cost = cost;
		this.supplierId = supplierId;
	}


	public PurchaseOrder() {
		super();
	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getOrdId() {
		return ordId;
	}



	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}



	public String getOrdType() {
		return ordType;
	}



	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}



	public String getOrdStatus() {
		return ordStatus;
	}



	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getDeliveryAddress() {
		return deliveryAddress;
	}



	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}



	public Date getOrdDate() {
		return ordDate;
	}



	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}



	public Date getDeliveryDate() {
		return deliveryDate;
	}



	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	


	public List<Item> getItemIdList() {
		return itemIdList;
	}



	public void setItemIdList(List<Item> itemIdList) {
		this.itemIdList = itemIdList;
	}





	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public String getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	
	

}
