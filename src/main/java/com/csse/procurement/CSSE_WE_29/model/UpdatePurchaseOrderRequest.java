package com.csse.procurement.CSSE_WE_29.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.csse.procurement.CSSE_WE_29.entity.Item;

public class UpdatePurchaseOrderRequest {
	
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
	private String siteId;
	
	
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
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	
}

