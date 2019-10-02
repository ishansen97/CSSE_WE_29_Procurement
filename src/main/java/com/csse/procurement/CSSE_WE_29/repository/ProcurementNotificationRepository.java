package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.entity.ProcurementNotification;

@Repository
public interface ProcurementNotificationRepository extends MongoRepository<ProcurementNotification, String> {
	public Notification findTop1ByOrderByNotificationIdDesc();
	public Notification findByNotificationId(int notification_Id);
}
