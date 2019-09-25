package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document (collection = "Assign_Budget")
@Component
public class AssignBudget {

	@Id
	private String _id;
	private int budgetId;
	private String siteId;
	private double siteBudget;
	
	

	public AssignBudget(String _id, int budgetId, String siteId, double siteBudget) {
		super();
		this._id = _id;
		this.budgetId = budgetId;
		this.siteId = siteId;
		this.siteBudget = siteBudget;
	}

	

	public AssignBudget(int budgetId, String siteId, double siteBudget) {
		super();
		this.budgetId = budgetId;
		this.siteId = siteId;
		this.siteBudget = siteBudget;
	}


	public AssignBudget(String siteId, double siteBudget) {
		super();
		this.siteId = siteId;
		this.siteBudget = siteBudget;
	}


	public AssignBudget() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}

	public String getSiteId() {
		return siteId;
	}



	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}



	public double getSiteBudget() {
		return siteBudget;
	}

	public void setSiteBudget(double siteBudget) {
		this.siteBudget = siteBudget;
	}
	
	
}
