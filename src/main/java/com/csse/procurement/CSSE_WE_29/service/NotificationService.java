package com.csse.procurement.CSSE_WE_29.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.NotificationConstants;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.entity.ProcurementNotification;
import com.csse.procurement.CSSE_WE_29.repository.ManagerNotificationRepository;
import com.csse.procurement.CSSE_WE_29.repository.NotificationRepository;
import com.csse.procurement.CSSE_WE_29.repository.ProcurementNotificationRepository;
import com.csse.procurement.CSSE_WE_29.repository.SupplierNotifcationRepository;

@Service
public class NotificationService implements NotificationListener {

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private NotificationConstants notificationConstants;
	
	public List<Notification> findAllNotifications() {
		return notificationRepository.findAll();
	}
	
	public Notification findByNotificationId(int notification_Id) {
		return notificationRepository.findByNotificationId(notification_Id);
	}
	
	public boolean saveNotification(Notification notification) {
		try {
			int notificationId = createNotificationId();
			notification.setNotificationId(notificationId);
			
			notificationRepository.save(notification);
			
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}


	public boolean deleteNotification(Notification notification) {
		try {
			notificationRepository.delete(notification);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public int createNotificationId() {
		Notification notification = notificationRepository.findTop1ByOrderByNotificationIdDesc();
		int last_Id = 0;
		int new_Id = 0;
		
		try {
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
			notificationRepository.save(notification);
		} catch (Exception ex) {
			
		}
		return true;
	}
	

}
