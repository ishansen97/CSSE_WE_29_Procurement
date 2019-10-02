package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.SupplierConstants;
import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.entity.Supplier;
import com.csse.procurement.CSSE_WE_29.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	public List<Supplier> findAllSuppliers() {
		return supplierRepository.findAll();
	}
	
	public Supplier findBySupplierId(String supplierId) {
		return supplierRepository.findBySupplierId(supplierId);
	}
	
	public boolean savePurchaseOrder(Supplier supplier) {
		
		String supplier_Id = createSupplierId();
		supplier.setSupplierId(supplier_Id);
		
		try {
			supplierRepository.save(supplier);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public String createSupplierId() {
		Supplier supplier = supplierRepository.findTop1ByOrderBySupplierIdDesc();
		String last_Id = null;
		String new_Id = null;
		
		try {
			last_Id = supplier.getSupplierId();
			
			if (last_Id != null) {
	            String[] idParts = last_Id.split(SupplierConstants.ID_PREFIX);
	
	            String subStr = last_Id.substring(last_Id.indexOf(SupplierConstants.ID_PREFIX) + 2);
	
	            if (subStr.startsWith("0")) {
	                int index = subStr.lastIndexOf("0");
	                String numSubStr = subStr.substring(index + 1);
	               
	
	                int number = Integer.parseInt(numSubStr);
	                number++;
	                subStr = subStr.replaceAll(numSubStr, number + "");
	
	                new_Id = SupplierConstants.ID_PREFIX + subStr;
	            }
	
	            else {
	                int number = Integer.parseInt(subStr);
	                number++;
	
	                new_Id = SupplierConstants.ID_PREFIX + subStr;
	            }
	           
	        }
	        else {
	            new_Id = SupplierConstants.DEFAULT_ID;
	        }
			
		} catch (NullPointerException ex) {
			new_Id = SupplierConstants.DEFAULT_ID;
		}
		
		return new_Id;
//		return null;
	}
}
