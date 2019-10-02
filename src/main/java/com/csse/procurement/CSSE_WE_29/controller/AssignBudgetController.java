package com.csse.procurement.CSSE_WE_29.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csse.procurement.CSSE_WE_29.entity.AssignBudget;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.service.AssignBudgetService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = {"authorization", "content-type"})
public class AssignBudgetController {

	@Autowired
	private AssignBudgetService assignBudgetService;
	
	@RequestMapping("api/budget/get-all-budgets")
	public ResponseEntity<List<AssignBudget>> findAll() {
		List<AssignBudget> list = null;
		list = assignBudgetService.findAll();
		
		if (list != null)
			return new ResponseEntity<>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "api/budget/insert-budget")
	public ResponseEntity<Boolean> insertBudget(@RequestBody AssignBudget assignBudget) {
		boolean isInserted = assignBudgetService.save(assignBudget);
		
		if (isInserted)
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isInserted), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "api/budget/update-budget")
	public ResponseEntity<Boolean> updateBudget(@RequestBody AssignBudget assignBudget) {
		boolean isUpdated = assignBudgetService.update(assignBudget);
		
		if (isUpdated)
			return new ResponseEntity<Boolean>(new Boolean(isUpdated), HttpStatus.OK);
		else
			return new ResponseEntity<Boolean>(new Boolean(isUpdated), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@RequestMapping("api/budget/get-budget/{budgetId}")
	public ResponseEntity<AssignBudget> getBudget(@PathVariable("budgetId") int budgetId) {
		AssignBudget assignBudget = null;
		assignBudget = assignBudgetService.findByBudgetId(budgetId);
		
		if (assignBudget != null)
			return new ResponseEntity<AssignBudget>(assignBudget, HttpStatus.OK);
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
