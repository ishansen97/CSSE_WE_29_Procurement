package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.csse.procurement.CSSE_WE_29.entity.Notification;

@Component
public interface NotificationListener {
	public List<? extends Notification> findAllNotifications();
	public Notification findByNotificationId(int notification_Id);
	public boolean saveNotification(Notification notification);
	public boolean deleteItem(Notification notification);
	public boolean updateReadStatus(Notification notification);
}
