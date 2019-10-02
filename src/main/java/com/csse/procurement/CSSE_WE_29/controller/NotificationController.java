package com.csse.procurement.CSSE_WE_29.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.service.NotificationService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("api/notification/get-all-notifications")
	public ResponseEntity<List<Notification>> getItems() {
		List<Notification> notifications = null;
		notifications = notificationService.findAllNotifications();
		
		if (notifications != null)
			return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/notification/insert-notification")
	public ResponseEntity<Boolean> insertItem(@RequestBody Notification notification) {
		boolean isInserted = notificationService.saveNotification(notification);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping("api/notification/get-notification/{notificationId}")
	public ResponseEntity<Notification> getItem(@PathVariable("notificationId") int notificationId) {
		Notification notification = null;
		notification = notificationService.findByNotificationId(notificationId);
		
		if (notification != null)
			return new ResponseEntity<Notification>(notification, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "api/notification/read-notification")
	public ResponseEntity<Boolean> readNotification(@RequestBody Notification notification) {
		boolean isRead = notificationService.updateReadStatus(notification);
		
		return new ResponseEntity<Boolean>(new Boolean(isRead), HttpStatus.OK);
	}
}
