package com.csse.procurement.CSSE_WE_29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csse.procurement.CSSE_WE_29.constants.ProcurementStaffConstants;
import com.csse.procurement.CSSE_WE_29.entity.ProcurementStaff;
import com.csse.procurement.CSSE_WE_29.repository.ProcurementStaffRepository;

@Service
public class ProcurementStaffService {

	@Autowired
	private ProcurementStaffRepository procurementStaffRepository;
//	@Autowired
//	private ProcurementStaff procurementStaff;
	@Autowired
	private ProcurementStaffConstants procurementStaffConstants;
	
	public List<ProcurementStaff> getAll() {
		return procurementStaffRepository.findAll();
	}
	
	public ProcurementStaff getById(String Id) {
		return procurementStaffRepository.findByStaffId(Id);
	}
	
	public ProcurementStaff findLastRecord() {
		return procurementStaffRepository.findTop1ByOrderByStaffIdDesc();
	}
	
	public String createStaffId() {
		ProcurementStaff staff = procurementStaffRepository.findTop1ByOrderByStaffIdDesc();
		String last_Id = null;
		String new_Id = null;
		last_Id = staff.getStaffId();
		
		if (last_Id != null) {
            String[] idParts = last_Id.split(procurementStaffConstants.ID_PREFIX);

            String subStr = last_Id.substring(last_Id.indexOf(procurementStaffConstants.ID_PREFIX) + 1);

            if (subStr.startsWith("0")) {
                int index = subStr.lastIndexOf("0");
                String numSubStr = subStr.substring(index + 1);
               

                int number = Integer.parseInt(numSubStr);
                number++;
                subStr = subStr.replaceAll(numSubStr, number + "");

                new_Id = procurementStaffConstants.ID_PREFIX + subStr;
            }

            else {
                int number = Integer.parseInt(subStr);
                number++;

                new_Id = procurementStaffConstants.ID_PREFIX + subStr;
            }
           
        }
        else {
            new_Id = "S001";
        }
		
		return new_Id;
	}
	
	public boolean saveProcurementStaff(ProcurementStaff staff) {
		
		String staffId = createStaffId();
		
		staff.setStaffId(staffId);
		
		try {
			procurementStaffRepository.save(staff);
		} catch (Exception ex) {
			throw ex;
		}
		return true;
		
	}
	
	
	
}
