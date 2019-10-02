package com.csse.procurement.CSSE_WE_29.service;

import org.springframework.stereotype.Component;

import com.csse.procurement.CSSE_WE_29.entity.Notification;

@Component
public interface NotificationSubject {
	public void addNotificationListeners(NotificationListener listener);
	public void removeNotificationListeners(NotificationListener listener);
	public boolean setManagerNotification(Notification notification);
	public boolean setProcurementNotification(Notification notification);
	public boolean setProcurementNotificationForManager(Notification notification);
	public boolean setSiteManagerNotification(Notification notification);
	public boolean setSupplierNotification(Notification notification);
}
