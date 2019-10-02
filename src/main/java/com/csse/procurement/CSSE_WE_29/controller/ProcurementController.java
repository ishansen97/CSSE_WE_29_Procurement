package com.csse.procurement.CSSE_WE_29.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csse.procurement.CSSE_WE_29.entity.ProcurementStaff;
import com.csse.procurement.CSSE_WE_29.service.ProcurementStaffService;

@RestController
public class ProcurementController {
	
	@Autowired
	private ProcurementStaffService procService;

	@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
	@RequestMapping("api/procurement/get-procurement/{Id}")
	public ResponseEntity<ProcurementStaff> getProcurementStaff(@PathVariable("Id") String Id) {
		ProcurementStaff staff = null;
		staff = procService.getById(Id);
		
		if (staff != null)
			return new ResponseEntity<ProcurementStaff>(staff, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
	@RequestMapping(method = RequestMethod.POST, value = "api/procurement/insert-procurement")
	public ResponseEntity<Boolean> insertProcurementStaff(@RequestBody ProcurementStaff procStaff) {
		boolean isInserted = procService.saveProcurementStaff(procStaff);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
	@RequestMapping("api/procurement/get-last-procurement")
	public ResponseEntity<ProcurementStaff> getLastProcurementStaff() {
		ProcurementStaff staff = null;
		staff = procService.findLastRecord();
		
		if (staff != null)
			return new ResponseEntity<ProcurementStaff>(staff, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
