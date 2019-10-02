package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;

@Document(collection = "Supplier")
@Component
public class Supplier {
	
	@Id
	private String Id;
	private String supplierId;
	private String supplierName;
	private String supplierLocation;
	
	

	public Supplier(String id, String supplierId, String supplierName, String supplierLocation) {
		super();
		Id = id;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierLocation = supplierLocation;
	}

	

	public Supplier(String supplierId, String supplierName, String supplierLocation) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierLocation = supplierLocation;
	}

	public Supplier(String supplierName, String supplierLocation) {
		super();
		this.supplierName = supplierName;
		this.supplierLocation = supplierLocation;
	}



	public Supplier() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierLocation() {
		return supplierLocation;
	}

	public void setSupplierLocation(String supplierLocation) {
		this.supplierLocation = supplierLocation;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
	
	
}
