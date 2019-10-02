package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Site")
@Component
public class Site {

	@Id
	private String id;
	private String siteId;
	private String siteName;
	
	public Site(String id, String siteId, String siteName) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.siteName = siteName;
	}

	public Site(String siteId, String siteName) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
	}

	public Site(String siteName) {
		super();
		this.siteName = siteName;
	}

	public Site() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
