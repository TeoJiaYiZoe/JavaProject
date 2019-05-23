package sg.nus.iss.mvc.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.model.leaveBalance;
import sg.nus.iss.mvc.model.leaveType;

public interface leaveBalanceService {
	
	leaveBalance findByStaffAndLeavetype(Staff staff, leaveType leavetype);
	List<leaveBalance> findByStaff(Staff staff);
	
	void saveBalanceByStaffAndType(leaveType leavetype, int bal, Staff staff);

}
