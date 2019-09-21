package com.csse.procurement.CSSE_WE_29.entity;

import java.util.Date;

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
	private PurchaseOrder purchaseOrder;
	private boolean isRead;
	private String receiverType;
	private Date publishedDate;
	private Date readDate;
	
	


	public Notification(String _id, int notificationId, String message, PurchaseOrder purchaseOrder, boolean isRead,
			String receiverType, Date publishedDate, Date readDate) {
		super();
		this._id = _id;
		this.notificationId = notificationId;
		this.message = message;
		this.purchaseOrder = purchaseOrder;
		this.isRead = isRead;
		this.receiverType = receiverType;
		this.publishedDate = publishedDate;
		this.readDate = readDate;
	}



	public Notification(int notificationId, String message, PurchaseOrder purchaseOrder, boolean isRead,
			String receiverType, Date publishedDate, Date readDate) {
		super();
		this.notificationId = notificationId;
		this.message = message;
		this.purchaseOrder = purchaseOrder;
		this.isRead = isRead;
		this.receiverType = receiverType;
		this.publishedDate = publishedDate;
		this.readDate = readDate;
	}


	public Notification(String message, PurchaseOrder purchaseOrder, boolean isRead, String receiverType,
			Date publishedDate, Date readDate) {
		super();
		this.message = message;
		this.purchaseOrder = purchaseOrder;
		this.isRead = isRead;
		this.receiverType = receiverType;
		this.publishedDate = publishedDate;
		this.readDate = readDate;
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

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	public String getReceiverType() {
		return receiverType;
	}


	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
	
	
}
