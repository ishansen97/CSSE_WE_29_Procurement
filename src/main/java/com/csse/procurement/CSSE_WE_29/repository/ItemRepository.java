package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
	public Item findTop1ByOrderByItemIdDesc();
	public Item findByItemId(int itemId);
}
