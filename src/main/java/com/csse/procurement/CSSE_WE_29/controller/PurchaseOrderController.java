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

import com.csse.procurement.CSSE_WE_29.entity.ProcurementStaff;
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.model.PurchaseOrderResponse;
import com.csse.procurement.CSSE_WE_29.model.UpdatePurchaseOrderRequest;
import com.csse.procurement.CSSE_WE_29.model.UpdatePurchaseOrderResponse;
import com.csse.procurement.CSSE_WE_29.service.PurchaseOrderService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@RequestMapping("api/purchase/get-all-purchase-orders")
	public ResponseEntity<List<PurchaseOrderResponse>> getPurchaseOrders() {
		List<PurchaseOrderResponse> purchaseOrders = null;
		purchaseOrders = purchaseOrderService.findAllPurchaseOrders();
		
		if (purchaseOrders != null)
			return new ResponseEntity<List<PurchaseOrderResponse>>(purchaseOrders, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/purchase/insert-purchase-order")
	public ResponseEntity<Boolean> insertPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
		boolean isInserted = purchaseOrderService.savePurchaseOrder(purchaseOrder);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping("api/purchase/get-purchase-order/{orderId}")
	public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable("orderId") String orderId) {
		PurchaseOrder purchaseOrder = null;
		purchaseOrder = purchaseOrderService.findByOrderId(orderId);
		
		if (purchaseOrder != null)
			return new ResponseEntity<PurchaseOrder>(purchaseOrder, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "api/purchase/update-purchase-order")
	public ResponseEntity<UpdatePurchaseOrderResponse> updatePurchaseOrder(@RequestBody UpdatePurchaseOrderRequest request) {
		UpdatePurchaseOrderResponse response = purchaseOrderService.updatePurchaseOrder(request);
		
		return new ResponseEntity<UpdatePurchaseOrderResponse>(response, HttpStatus.OK);
	}
	
}
