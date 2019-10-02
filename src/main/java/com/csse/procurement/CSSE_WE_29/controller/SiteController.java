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
import com.csse.procurement.CSSE_WE_29.entity.Site;
import com.csse.procurement.CSSE_WE_29.service.NotificationService;
import com.csse.procurement.CSSE_WE_29.service.SiteService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
public class SiteController {

	@Autowired
	private SiteService siteService;
	
	@RequestMapping("api/site/get-all-sites")
	public ResponseEntity<List<Site>> getSites() {
		List<Site> sites = null;
		sites = siteService.findAll();
		
		if (sites != null)
			return new ResponseEntity<List<Site>>(sites, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/site/insert-site")
	public ResponseEntity<Boolean> insertItem(@RequestBody Site site) {
		boolean isInserted = siteService.save(site);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping("api/site/get-site/{siteId}")
	public ResponseEntity<Site> getSite(@PathVariable("siteId") String siteId) {
		Site site = null;
		site = siteService.findBySiteId(siteId);
		
		if (site != null)
			return new ResponseEntity<Site>(site, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "api/site/update-site")
	public ResponseEntity<Boolean> readNotification(@RequestBody Site site) {
		boolean isRead = siteService.update(site);
		
		return new ResponseEntity<Boolean>(new Boolean(isRead), HttpStatus.OK);
	}
}
