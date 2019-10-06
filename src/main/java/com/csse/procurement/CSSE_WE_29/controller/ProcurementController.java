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
@CrossOrigin(origins = {"*"}, allowedHeaders = {"authorization", "content-type"})
public class ProcurementController {
	
	@Autowired
	private ProcurementStaffService procService;

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
	
	@RequestMapping(method = RequestMethod.POST, value = "api/procurement/insert-procurement")
	public ResponseEntity<Boolean> insertProcurementStaff(@RequestBody ProcurementStaff procStaff) {
		boolean isInserted = procService.saveProcurementStaff(procStaff);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "api/procurement/update-procurement")
	public ResponseEntity<Boolean> updateProcurementStaff(@RequestBody ProcurementStaff procStaff) {
		boolean isUpdated = procService.updateProcurementStaff(procStaff);
		
		if (isUpdated)
			return new ResponseEntity<Boolean>(new Boolean(isUpdated), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isUpdated), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "api/procurement/delete-procurement")
	public ResponseEntity<Boolean> deleteProcurementStaff(@RequestBody ProcurementStaff procStaff) {
		boolean isDeleted = procService.deleteProcurementStaff(procStaff);
		
		if (isDeleted)
			return new ResponseEntity<Boolean>(new Boolean(isDeleted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isDeleted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
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
