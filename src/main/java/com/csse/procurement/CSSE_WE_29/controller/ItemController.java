package com.csse.procurement.CSSE_WE_29.controller;

import java.util.ArrayList;
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

import com.csse.procurement.CSSE_WE_29.entity.Item;
import com.csse.procurement.CSSE_WE_29.model.ItemForSiteManager;
import com.csse.procurement.CSSE_WE_29.service.ItemService;
import com.csse.procurement.CSSE_WE_29.service.SupplierService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private SupplierService supplierService;

	@RequestMapping("api/item/get-all-items")
	public ResponseEntity<List<Item>> getItems() {
		List<Item> items = null;
		items = itemService.findAllItems();
		
		if (items != null)
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("api/item/get-all-items-for-site-manager")
	public ResponseEntity<List<ItemForSiteManager>> getItemsForSiteManager() {
		List<Item> items = null;
		List<ItemForSiteManager> itemsForSiteManager = new ArrayList<ItemForSiteManager>();
		items = itemService.findAllItems();
		
		for(Item i : items) {
			itemsForSiteManager.add(
					new ItemForSiteManager(
							i.getItemId(), 
							i.getItemPrice(), 
							i.getItemType(), 
							i.getItemQuantity(), 
							i.getItemMetric(), 
							i.getSupplierId(), 
							supplierService.findBySupplierId(i.getSupplierId()).getSupplierName(), 
							i.isCritical())
					);
		}
		
		if (itemsForSiteManager != null)
			return new ResponseEntity<List<ItemForSiteManager>>(itemsForSiteManager, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/item/insert-item")
	public ResponseEntity<Boolean> insertItem(@RequestBody Item item) {
		boolean isInserted = itemService.saveItem(item);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping("api/item/get-item/{itemId}")
	public ResponseEntity<Item> getItem(@PathVariable("itemId") int itemId) {
		Item item = null;
		item = itemService.findByItemId(itemId);
		
		if (item != null)
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
