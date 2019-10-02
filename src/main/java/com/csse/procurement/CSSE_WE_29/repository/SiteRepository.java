package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.Site;

@Repository
public interface SiteRepository extends MongoRepository<Site, String> {
	public Site findTop1ByOrderBySiteIdDesc();
	public Site findBySiteId(String siteId);
	public void deleteBySiteId(String siteId);
}
