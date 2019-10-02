package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.NotificationConstants;
import com.csse.procurement.CSSE_WE_29.entity.ManagerNotification;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.entity.ProcurementNotification;
import com.csse.procurement.CSSE_WE_29.repository.NotificationRepository;
import com.csse.procurement.CSSE_WE_29.repository.ProcurementNotificationRepository;

@Service
public class ProcurementNotificationService implements NotificationListener {

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private ProcurementNotificationRepository procurementNotificationRepository;
	@Autowired
	private NotificationConstants notificationConstants;
	
	public List<ProcurementNotification> findAllNotifications() {
		return procurementNotificationRepository.findAll();
	}
	
	public Notification findByNotificationId(int notification_Id) {
		return procurementNotificationRepository.findByNotificationId(notification_Id);
	}
	
	public boolean saveNotification(Notification notification) {
		try {
			int notificationId = createNotificationId();
			notification.set_id(null);
			notification.setNotificationId(notificationId);
			notification.setReceiverType("Procurement");
			notificationRepository.insert(notification);
			
			
			ProcurementNotification procurementNotification = new ProcurementNotification();
			procurementNotification.setNotificationId(notificationId);
			procurementNotification.setSender(notification.getSender());
			procurementNotification.setReceiverType("Procurement");
			procurementNotification.setMessage(notification.getMessage());
			procurementNotification.setPublishedDate(notification.getPublishedDate());
			procurementNotification.setReadDate(notification.getReadDate());
			procurementNotification.setPurchaseOrder(notification.getPurchaseOrder());
			procurementNotification.setItems(notification.getItems());
			procurementNotification.setSupplier(notification.getSupplier());
			
			procurementNotificationRepository.save(procurementNotification);
			
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}


	public boolean deleteItem(Notification notification) {
		try {
			procurementNotificationRepository.delete((ProcurementNotification) notification);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public int createNotificationId() {
		
		int last_Id = 0;
		int new_Id = 0;
		
		try {
			Notification notification = procurementNotificationRepository.findTop1ByOrderByNotificationIdDesc();
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
			procurementNotificationRepository.save((ProcurementNotification) notification);
		} catch (Exception ex) {
			
		}
		return true;
	}
}
