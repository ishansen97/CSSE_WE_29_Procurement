package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.ItemConstants;
import com.csse.procurement.CSSE_WE_29.entity.Item;
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemConstants itemConstants;
	
	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}
	
	public Item findByItemId(int itemId) {
		return itemRepository.findByItemId(itemId);
	}
	
	public boolean saveItem(Item item) {
		try {
			int itemId = createItemId();
			item.setItemId(itemId);
			
			itemRepository.save(item);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public boolean deleteItem(Item item) {
		try {
			itemRepository.delete(item);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public int createItemId() {
		Item item = itemRepository.findTop1ByOrderByItemIdDesc();
		int last_Id = 0;
		int new_Id = 0;
		
		try {
			last_Id = item.getItemId();
			
			if (last_Id != 0) {
	            new_Id = last_Id + 1;
	           
	        }
			
		} catch (NullPointerException ex) {
			new_Id = itemConstants.DEFAULT_ID;
		}
		
		return new_Id;
	}
	
	public boolean updateItem(Item item) {
		try {
			itemRepository.save(item);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
}
