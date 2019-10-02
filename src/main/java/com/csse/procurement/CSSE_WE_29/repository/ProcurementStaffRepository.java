package com.csse.procurement.CSSE_WE_29.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.csse.procurement.CSSE_WE_29.entity.ProcurementStaff;

@Repository
public interface ProcurementStaffRepository extends MongoRepository<ProcurementStaff, String> {
	public ProcurementStaff findByStaffId(String staffId);
	public ProcurementStaff findTop1ByOrderByStaffIdDesc();
}
