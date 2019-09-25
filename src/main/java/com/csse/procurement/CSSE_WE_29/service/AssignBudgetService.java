package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.AssignBudgetConstants;
import com.csse.procurement.CSSE_WE_29.entity.AssignBudget;
import com.csse.procurement.CSSE_WE_29.entity.Notification;
import com.csse.procurement.CSSE_WE_29.repository.AssignBudgetRepository;

@Service
public class AssignBudgetService {

	@Autowired
	private AssignBudgetRepository assignBudgetRepository;
	@Autowired
	private AssignBudgetConstants assignBudgetConstants;
	
	public List<AssignBudget> findAll() {
		return assignBudgetRepository.findAll();
	}
	
	public boolean save(AssignBudget assignBudget) {
		try {
			int budgetId = generateBudgetId();
			assignBudget.setBudgetId(budgetId);
			
			assignBudgetRepository.save(assignBudget);
			
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}
	
	public boolean update(AssignBudget assignBudget) {
		try {
			assignBudgetRepository.save(assignBudget);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public AssignBudget findByBudgetId(int budgetId) {
		return assignBudgetRepository.findByBudgetId(budgetId);
		
	}
	
	public boolean deleteBudget(AssignBudget assignBudget) {
		try {
			assignBudgetRepository.delete(assignBudget);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public int generateBudgetId() {
		AssignBudget assignBudget = assignBudgetRepository.findTop1ByOrderByBudgetIdDesc();
		int last_Id = 0;
		int new_Id = 0;
		
		try {
			last_Id = assignBudget.getBudgetId();
			
			if (last_Id != 0) {
	            new_Id = last_Id + 1;
	           
	        }
			
		} catch (NullPointerException ex) {
			new_Id = assignBudgetConstants.DEFAULT_ID;
		}
		
		return new_Id;
	}
}
