package com.csse.procurement.CSSE_WE_29.model;

import org.springframework.stereotype.Component;

@Component
public class UpdatePurchaseOrderResponse {
	private boolean IsUpdated;

	public boolean isIsUpdated() {
		return IsUpdated;
	}

	public void setIsUpdated(boolean isUpdated) {
		IsUpdated = isUpdated;
	}
	
	
}
