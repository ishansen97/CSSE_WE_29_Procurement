package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.NotificationConstants;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.entity.SiteManagerNotification;
import com.csse.procurement.CSSE_WE_29.entity.SupplierNotification;
import com.csse.procurement.CSSE_WE_29.repository.NotificationRepository;
import com.csse.procurement.CSSE_WE_29.repository.SupplierNotifcationRepository;

@Service
public class SupplierNotificationService implements NotificationListener {
	
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private SupplierNotifcationRepository supplierNotificationRepository;
	@Autowired
	private NotificationConstants notificationConstants;

	public List<SupplierNotification> findAllNotifications() {
		return supplierNotificationRepository.findAll();
	}
	
	public Notification findByNotificationId(int notification_Id) {
		return supplierNotificationRepository.findByNotificationId(notification_Id);
	}
	
	public boolean saveNotification(Notification notification) {
		try {
			int notificationId = createNotificationId();
			notification.set_id(null);
			notification.setNotificationId(notificationId);
			notification.setReceiverType("Supplier");
			notificationRepository.insert(notification);
			
			System.out.println("I am here");
			
			SupplierNotification supplierNotification = new SupplierNotification();
			supplierNotification.setNotificationId(notificationId);
			supplierNotification.setSender(notification.getSender());
			supplierNotification.setReceiverType("Supplier");
			supplierNotification.setMessage(notification.getMessage());
			supplierNotification.setPublishedDate(notification.getPublishedDate());
			supplierNotification.setReadDate(notification.getReadDate());
			supplierNotification.setPurchaseOrder(notification.getPurchaseOrder());
			supplierNotification.setItems(notification.getItems());
			supplierNotification.setSupplier(notification.getSupplier());
			
			supplierNotificationRepository.save(supplierNotification);
			
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}


	public boolean deleteNotification(Notification notification) {
		try {
			supplierNotificationRepository.delete((SupplierNotification) notification);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public int createNotificationId() {
		
		int last_Id = 0;
		int new_Id = 0;
		
		try {
			Notification notification = supplierNotificationRepository.findTop1ByOrderByNotificationIdDesc();
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
			supplierNotificationRepository.save((SupplierNotification) notification);
		} catch (Exception ex) {
			
		}
		return true;
	}
}
