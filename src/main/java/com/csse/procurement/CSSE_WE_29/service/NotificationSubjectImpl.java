package com.csse.procurement.CSSE_WE_29.service;

import java.util.ArrayList;
import java.util.Arrays;
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
	private List<NotificationListener> procurement_listeners = new ArrayList<>(Arrays.asList(siteManagerNotificationService, supplierNotificationService));
	private List<NotificationListener> manager_listeners = new ArrayList<>(Arrays.asList(managerNotificationService, siteManagerNotificationService, supplierNotificationService));
	private List<NotificationListener> siteManager_listeners = new ArrayList<>(Arrays.asList(supplierNotificationService));
	private List<NotificationListener> procurement_to_manager = new ArrayList<>(Arrays.asList(managerNotificationService));
	private List<NotificationListener> supplier_listners = new ArrayList<>(Arrays.asList(procurementNotificationService));
	
	@Override
	public void addNotificationListeners(NotificationListener listener) {
		listeners.add(listener);
		
	}
	
	@Override
	public void removeNotificationListeners(NotificationListener listener) {
		listeners.remove(listener);
		
	}
	
	@Override
	public boolean setManagerNotification(Notification notification) {
		listeners.clear();
		listeners.add(procurementNotificationService);
		listeners.add(siteManagerNotificationService);
		listeners.add(supplierNotificationService);
		this.notification = notification;
		try {
			notifyListeners();
		} catch (Exception e) {
			throw e;
		}
		return true;
		
	}
	
	
	@Override
	public boolean setSiteManagerNotification(Notification notification) {
		listeners.clear();
		listeners.add(supplierNotificationService);
		this.notification = notification;
		try {
			notifyListeners();
		} catch (Exception e) {
			throw e;
		}
		return true;
		
				
	}
	
	@Override
	public boolean setProcurementNotification(Notification notification) {
		listeners.clear();
		listeners.add(siteManagerNotificationService);
		listeners.add(supplierNotificationService);
		this.notification = notification;
		try {
			notifyListeners();
		} catch (Exception e) {
			throw e;
		}
		return true;
		
	}
	
	@Override
	public boolean setProcurementNotificationForManager(Notification notification) {
		listeners.clear();
		listeners.add(managerNotificationService);
		this.notification = notification;
		try {
			notifyListeners();
		} catch (Exception e) {
			throw e;
		}
		return true;		
	}
	
	@Override
	public boolean setSupplierNotification(Notification notification) {
		// TODO Auto-generated method stub
		try {
			notifyListeners();
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	public void notifyListeners() {
		for (NotificationListener listener: listeners) {
			listener.saveNotification(notification);
		}
	}
}
