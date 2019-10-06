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

import com.csse.procurement.CSSE_WE_29.entity.PurchaseOrder;
import com.csse.procurement.CSSE_WE_29.entity.Supplier;
import com.csse.procurement.CSSE_WE_29.service.SupplierService;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = {"authorization", "content-type"})
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping("api/supplier/get-all-suppliers")
	public ResponseEntity<List<Supplier>> getSuppliers() {
		List<Supplier> suppliers = null;
		suppliers = supplierService.findAllSuppliers();
		
		if (suppliers != null)
			return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "api/supplier/insert-supplier")
	public ResponseEntity<Boolean> insertSupplier(@RequestBody Supplier supplier) {
		boolean isInserted = supplierService.savePurchaseOrder(supplier);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping("api/supplier/get-supplier/{supplierId}")
	public ResponseEntity<Supplier> getSupplier(@PathVariable("supplierId") String supplierId) {
		Supplier supplier = null;
		supplier = supplierService.findBySupplierId(supplierId);
		
		if (supplier != null)
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
