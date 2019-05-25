package sg.nus.iss.mvc.service;

import java.util.List;

import sg.nus.iss.mvc.javabean.LeaveDetailsBean;
import sg.nus.iss.mvc.model.LeaveType;

public interface LeaveTypeService {

	LeaveDetailsBean createBean(Integer leavetypeid, Integer designationid);
	void saveAndPopulate(LeaveType leavetype);
	void deleteType(Integer leavetypeid); 
	void updateSaveHoliday(LeaveDetailsBean ldbean);
	List<LeaveType> getLeaveTypeList();
	
	
}