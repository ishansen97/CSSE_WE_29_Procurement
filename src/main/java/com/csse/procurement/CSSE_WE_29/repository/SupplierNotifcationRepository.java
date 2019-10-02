package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.entity.SupplierNotification;

public interface SupplierNotifcationRepository extends MongoRepository<SupplierNotification, String> {
	public Notification findTop1ByOrderByNotificationIdDesc();
	public Notification findByNotificationId(int notification_Id);
}
