package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
	public Notification findTop1ByOrderByNotificationIdDesc();
	public Notification findByNotificationId(int notification_Id);
}
