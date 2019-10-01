package com.csse.procurement.CSSE_WE_29.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.entity.Notification;

@Service
public class NotificationSubjectImpl implements NotificationSubject{
	
	@Autowired
	private ManagerNotificationService managerNotificationService;
	@Autowired
	private SiteManagerNotificationService siteManagerNotificationService;
	@Autowired
	private ProcurementNotificationService procurementNotificationService;
	@Autowired
	private SupplierNotificationService supplierNotificationService;
	
	private Notification notification;
	private List<NotificationListener> listeners = new ArrayList<>();
	
	@Override
	public void addNotificationListeners(NotificationListener listener) {
		listeners.add(listener);
		
	}
	
	@Override
	public void removeNotificationListeners(NotificationListener listener) {
		listeners.remove(listener);
		
	}
	
	@Override
	public void setManagerNotification(Notification notification) {
		listeners.add(new ProcurementNotificationService());
		listeners.add(new SiteManagerNotificationService());
		listeners.add(new SupplierNotificationService());
		
		this.notification = notification;
		notifyListeners();
	}
	
	
	@Override
	public void setSiteManagerNotification(Notification notification) {
		listeners.add(supplierNotificationService);
		this.notification = notification;
		notifyListeners();		
	}
	
	@Override
	public void setProcurementNotification(Notification notification) {
		listeners.add(siteManagerNotificationService);
		listeners.add(supplierNotificationService);
		this.notification = notification;
		notifyListeners();
		
	}
	
	@Override
	public void setProcurementNotificationForManager(Notification notification) {
		listeners.add(managerNotificationService);
		this.notification = notification;
		notifyListeners();		
	}
	
	@Override
	public void setSupplierNotification(Notification notification) {
		// TODO Auto-generated method stub
		
	}
	
	public void notifyListeners() {
		for (NotificationListener listener: listeners) {
			listener.saveNotification(notification);
		}
	}
}
