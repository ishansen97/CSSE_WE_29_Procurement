package com.csse.procurement.CSSE_WE_29.service;

import org.springframework.stereotype.Component;

import com.csse.procurement.CSSE_WE_29.entity.Notification;

@Component
public interface NotificationSubject {
	public void addNotificationListeners(NotificationListener listener);
	public void removeNotificationListeners(NotificationListener listener);
	public void setManagerNotification(Notification notification);
	public void setProcurementNotification(Notification notification);
	public void setProcurementNotificationForManager(Notification notification);
	public void setSiteManagerNotification(Notification notification);
	public void setSupplierNotification(Notification notification);
}
