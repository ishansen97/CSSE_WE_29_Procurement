package com.csse.procurement.CSSE_WE_29.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Procurement_Staff")
@Component
public class ProcurementStaff implements Serializable {
	
	@Id
	private String Id;
	public String staffId;
	public String staffName;
	private String staffUserName;
	private String staffPassword;
	
	public ProcurementStaff(String staffId, String staffName, String staffUserName, String staffPassword) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffUserName = staffUserName;
		this.staffPassword = staffPassword;
	}
	
	public ProcurementStaff(String staffName, String staffUserName, String staffPassword) {
		super();
		this.staffName = staffName;
		this.staffUserName = staffUserName;
		this.staffPassword = staffPassword;
	}

	public ProcurementStaff() {
		super();
	}

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffUserName() {
		return staffUserName;
	}
	public void setStaffUserName(String staffUserName) {
		this.staffUserName = staffUserName;
	}
	public String getStaffPassword() {
		return staffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}
	
	
}
