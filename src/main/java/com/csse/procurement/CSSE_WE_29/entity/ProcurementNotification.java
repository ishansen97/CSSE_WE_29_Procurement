package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "ProcurementNotification")
@Component
public class ProcurementNotification extends Notification {
	
}
