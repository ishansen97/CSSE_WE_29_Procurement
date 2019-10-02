package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.AssignBudget;

@Repository
public interface AssignBudgetRepository extends MongoRepository<AssignBudget, String> {
	public AssignBudget findTop1ByOrderByBudgetIdDesc();
	public AssignBudget findByBudgetId(int budgetId);
}
