package sg.nus.iss.mvc.service;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.model.leaveBalance;
import sg.nus.iss.mvc.model.leaveType;
import sg.nus.iss.mvc.repo.leaveBalanceRepository;
import sg.nus.iss.mvc.repo.leaveTypeRepository;

@Service
public class leaveBalanceServiceImpl implements leaveBalanceService{
	
	@Resource
	private leaveBalanceRepository leaveBalanceRepo;
	@Resource
	private leaveTypeRepository leaveTypeRepo;
	
	@Override
	@Transactional
	public leaveBalance findByStaffAndLeavetype(Staff staff, leaveType leavetype) {
		leaveBalance bal = leaveBalanceRepo.findByStaffAndLeavetype(staff, leavetype);
		return bal;
	}
	
	@Override
	@Transactional
	public void saveBalanceByStaffAndType(leaveType leavetype, int bal, Staff staff) {
		leaveBalanceRepo.savebalanceByStaffAndType(leavetype.getTypeId(), bal, staff.getStaffId());
	}
}
