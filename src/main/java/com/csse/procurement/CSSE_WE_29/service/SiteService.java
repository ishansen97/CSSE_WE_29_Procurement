package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.SiteConstants;
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.entity.Site;
import com.csse.procurement.CSSE_WE_29.repository.SiteRepository;

@Service
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private SiteConstants siteConstants;
	
	public List<Site> findAll() {
		return siteRepository.findAll();
	}
	
	public boolean save(Site site) {
		try {
			String siteId = createSiteId();
			System.out.println("SiteId: " + siteId);
			site.setSiteId(siteId);
			siteRepository.save(site);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean update(Site site) {
		try {
			siteRepository.save(site);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean delete(String siteId) {
		try {
			siteRepository.deleteBySiteId(siteId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Site findBySiteId(String siteId) {
		return siteRepository.findBySiteId(siteId);
	}
	
	public String createSiteId() {
		Site site = siteRepository.findTop1ByOrderBySiteIdDesc();
		String last_Id = null;
		String new_Id = null;
		
		try {
			last_Id = site.getSiteId();
			
			if (last_Id != null) {
	            String[] idParts = last_Id.split(siteConstants.ID_PREFIX);
	
	            String subStr = last_Id.substring(last_Id.indexOf(siteConstants.ID_PREFIX) + 2);
	
	            if (subStr.startsWith("0")) {
	                int index = subStr.lastIndexOf("0");
	                String numSubStr = subStr.substring(index + 1);
	               
	
	                int number = Integer.parseInt(numSubStr);
	                number++;
	                subStr = subStr.replaceAll(numSubStr, number + "");
	
	                new_Id = siteConstants.ID_PREFIX + subStr;
	            }
	
	            else {
	                int number = Integer.parseInt(subStr);
	                number++;
	
	                new_Id = siteConstants.ID_PREFIX + subStr;
	            }
	           
	        }
	        else {
	            new_Id = siteConstants.DEFAULT_ID;
	        }
			
		} catch (NullPointerException ex) {
			new_Id = siteConstants.DEFAULT_ID;
		} catch (Exception ex) {
			ex.printStackTrace();
			new_Id = null;
		}
		
		return new_Id;
	}
}
