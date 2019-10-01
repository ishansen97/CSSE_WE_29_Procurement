package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.NotificationConstants;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.entity.ProcurementNotification;
import com.csse.procurement.CSSE_WE_29.entity.SiteManagerNotification;
import com.csse.procurement.CSSE_WE_29.repository.SiteManagerNotificationRepository;

@Service
public class SiteManagerNotificationService implements NotificationListener {

	@Autowired
	private SiteManagerNotificationRepository siteManagerNotificationRepository;
	@Autowired
	private NotificationConstants notificationConstants;

	public List<SiteManagerNotification> findAllNotifications() {
		return siteManagerNotificationRepository.findAll();
	}
	
	public Notification findByNotificationId(int notification_Id) {
		return siteManagerNotificationRepository.findByNotificationId(notification_Id);
	}
	
	public boolean saveNotification(Notification notification) {
		try {
			int notificationId = createNotificationId();
			SiteManagerNotification siteManagerNotification = new SiteManagerNotification();
			siteManagerNotification.setNotificationId(notificationId);
			siteManagerNotification.setReceiverType("SiteManager");
			siteManagerNotification.setMessage(notification.getMessage());
			siteManagerNotification.setPublishedDate(notification.getPublishedDate());
			siteManagerNotification.setReadDate(notification.getReadDate());
			siteManagerNotification.setPurchaseOrder(notification.getPurchaseOrder());
			siteManagerNotification.setItems(notification.getItems());
			siteManagerNotification.setSupplier(notification.getSupplier());
			
			
			
			siteManagerNotificationRepository.save(siteManagerNotification);
			
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}


	public boolean deleteItem(Notification notification) {
		try {
			siteManagerNotificationRepository.delete((SiteManagerNotification) notification);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public int createNotificationId() {
		
		int last_Id = 0;
		int new_Id = 0;
		
		try {
			Notification notification = siteManagerNotificationRepository.findTop1ByOrderByNotificationIdDesc();
			last_Id = notification.getNotificationId();
			
			if (last_Id != 0) {
	            new_Id = last_Id + 1;
	           
	        }
			
		} catch (NullPointerException ex) {
			new_Id = notificationConstants.DEFAULT_ID;
		}
		
		return new_Id;
	}
	
	public boolean updateReadStatus(Notification notification) {
		try {
			siteManagerNotificationRepository.save((SiteManagerNotification) notification);
		} catch (Exception ex) {
			
		}
		return true;
	}

}
