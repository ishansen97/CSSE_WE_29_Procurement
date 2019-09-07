package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Notifications")
@Component
public class Notification {
	
	@Id
	private String _id;
	private int notificationId;
	private String message;
	private boolean isRead;
	
	public Notification(String _id, int notificationId, String message, boolean isRead) {
		super();
		this._id = _id;
		this.notificationId = notificationId;
		this.message = message;
		this.isRead = isRead;
	}

	public Notification(int notificationId, String message, boolean isRead) {
		super();
		this.notificationId = notificationId;
		this.message = message;
		this.isRead = isRead;
	}

	public Notification(String message, boolean isRead) {
		super();
		this.message = message;
		this.isRead = isRead;
	}

	public Notification() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	
}
