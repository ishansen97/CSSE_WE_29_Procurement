package com.csse.procurement.CSSE_WE_29.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.PurchaseOrderConstants;
import com.csse.procurement.CSSE_WE_29.constants.SupplierConstants;
import com.csse.procurement.CSSE_WE_29.entity.Item;
import com.csse.procurement.CSSE_WE_29.entity.ProcurementStaff;
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.entity.Supplier;
import com.csse.procurement.CSSE_WE_29.model.PurchaseOrderResponse;
import com.csse.procurement.CSSE_WE_29.model.UpdatePurchaseOrderRequest;
import com.csse.procurement.CSSE_WE_29.model.UpdatePurchaseOrderResponse;
import com.csse.procurement.CSSE_WE_29.repository.ItemRepository;
import com.csse.procurement.CSSE_WE_29.repository.PurchaseOrderRepository;
import com.csse.procurement.CSSE_WE_29.repository.SupplierRepository;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private PurchaseOrderConstants purchaseOrderConstants;
	@Autowired
	private PurchaseOrderResponse purchaseOrderResponse;
	@Autowired
	private PurchaseOrder purchaseOrder;
	@Autowired
	private UpdatePurchaseOrderResponse updateResponse;
	
	public List<PurchaseOrderResponse> findAllPurchaseOrders() {
		List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		List<PurchaseOrderResponse> purchaseResponseList = new ArrayList<>();
		
		
		for (PurchaseOrder order: orders) {
			PurchaseOrderResponse response = new PurchaseOrderResponse();
			List<Item> itemList = new ArrayList<>();
			response.setPurchaseOrder(order);
			
			String supplierId = order.getSupplierId();
			Supplier supplier = supplierRepository.findBySupplierId(supplierId);
			String supplierName = supplier.getSupplierName();
			response.setSupplierDetails(supplier);
			
			List<Item> itemIdList = order.getItemIdList();
			
			for (Item item: itemIdList) {
				Item newItem = itemRepository.findByItemId(item.getItemId()); 
				itemList.add(newItem);
			}
			
			response.setItemDetails(itemList);
			purchaseResponseList.add(response);
			
		}
		return purchaseResponseList;
	}
	
	public boolean savePurchaseOrder(PurchaseOrder purchaseOrder) {
		
		String order_Id = createPurchaseOrderId();
		purchaseOrder.setOrdId(order_Id);
		
		try {
			purchaseOrderRepository.save(purchaseOrder);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public boolean deletePurchaseOrder(ObjectId _id) {
		try {
			purchaseOrderRepository.deleteById(_id);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public PurchaseOrder findByOrderId(String orderId) {
		return purchaseOrderRepository.findByOrdId(orderId);
	}
	
	public String createPurchaseOrderId() {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findTop1ByOrderByOrdIdDesc();
		String last_Id = null;
		String new_Id = null;
		
		try {
			last_Id = purchaseOrder.getOrdId();
			
			if (last_Id != null) {
	            String[] idParts = last_Id.split(purchaseOrderConstants.ID_PREFIX);
	
	            String subStr = last_Id.substring(last_Id.indexOf(purchaseOrderConstants.ID_PREFIX) + 1);
	
	            if (subStr.startsWith("0")) {
	                int index = subStr.lastIndexOf("0");
	                String numSubStr = subStr.substring(index + 1);
	               
	
	                int number = Integer.parseInt(numSubStr);
	                number++;
	                subStr = subStr.replaceAll(numSubStr, number + "");
	
	                new_Id = purchaseOrderConstants.ID_PREFIX + subStr;
	            }
	
	            else {
	                int number = Integer.parseInt(subStr);
	                number++;
	
	                new_Id = purchaseOrderConstants.ID_PREFIX + subStr;
	            }
	           
	        }
	        else {
	            new_Id = purchaseOrderConstants.DEFAULT_ID;
	        }
			
		} catch (NullPointerException ex) {
			new_Id = purchaseOrderConstants.DEFAULT_ID;
		}
		
		return new_Id;
//		return null;
	}
	
	public UpdatePurchaseOrderResponse updatePurchaseOrder(UpdatePurchaseOrderRequest request) {
		
		PurchaseOrder order = null;
		
		purchaseOrder.set_id(request.get_id());
		purchaseOrder.setOrdId(request.getOrdId());
		purchaseOrder.setOrdType(request.getOrdType());
		purchaseOrder.setOrdStatus(request.getOrdStatus());
		purchaseOrder.setCompanyName(request.getCompanyName());
		purchaseOrder.setDeliveryAddress(request.getDeliveryAddress());
		purchaseOrder.setOrdDate(request.getOrdDate());
		purchaseOrder.setDeliveryDate(request.getDeliveryDate());
		purchaseOrder.setItemIdList(request.getItemIdList());
		purchaseOrder.setQuantity(request.getQuantity());
		purchaseOrder.setCost(request.getCost());
		purchaseOrder.setSupplierId(request.getSupplierId());
		purchaseOrder.setSiteId(request.getSiteId());
		
		try {
			order = purchaseOrderRepository.save(purchaseOrder);
			
		} catch (Exception ex) {
			
		}
		
		updateResponse.setIsUpdated(true);
		return updateResponse;
	}
	
	
}
