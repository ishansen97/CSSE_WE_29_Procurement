package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.NotificationConstants;
import com.csse.procurement.CSSE_WE_29.entity.ManagerNotification;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.repository.ManagerNotificationRepository;
import com.csse.procurement.CSSE_WE_29.repository.NotificationRepository;

@Service
public class ManagerNotificationService implements NotificationListener {

	@Autowired
	private ManagerNotificationRepository managerNotificationRepository;
	@Autowired
	private NotificationConstants notificationConstants;
	
	public List<ManagerNotification> findAllNotifications() {
		return managerNotificationRepository.findAll();
	}
	
	public Notification findByNotificationId(int notification_Id) {
		return managerNotificationRepository.findByNotificationId(notification_Id);
	}
	
	public boolean saveNotification(Notification notification) {
		try {
			int notificationId = createNotificationId();
			ManagerNotification managerNotification = new ManagerNotification();
			managerNotification.setNotificationId(notificationId);
			managerNotification.setReceiverType("Manager");
			managerNotification.setMessage(notification.getMessage());
			managerNotification.setPublishedDate(notification.getPublishedDate());
			managerNotification.setReadDate(notification.getReadDate());
			managerNotification.setPurchaseOrder(notification.getPurchaseOrder());
			managerNotification.setItems(notification.getItems());
			managerNotification.setSupplier(notification.getSupplier());
			
			System.out.println("Not. " + managerNotificationRepository);
			
			managerNotificationRepository.save(managerNotification);
			
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}


	public boolean deleteItem(Notification notification) {
		try {
			managerNotificationRepository.delete((ManagerNotification) notification);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public int createNotificationId() {

		int last_Id = 0;
		int new_Id = 0;
		
		try {
			Notification notification = managerNotificationRepository.findTop1ByOrderByNotificationIdDesc();
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
			managerNotificationRepository.save((ManagerNotification) notification);
		} catch (Exception ex) {
			
		}
		return true;
	}

}
