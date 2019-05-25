package sg.nus.iss.mvc.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sg.nus.iss.mvc.javabean.LeaveDetailsBean;
import sg.nus.iss.mvc.model.Designation;
import sg.nus.iss.mvc.model.LeaveDetails;
import sg.nus.iss.mvc.model.LeaveType;
import sg.nus.iss.mvc.repo.DesignationRepository;
import sg.nus.iss.mvc.repo.LeaveBalanceRepository;
import sg.nus.iss.mvc.repo.LeaveDetailsRepository;
import sg.nus.iss.mvc.repo.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Resource
	private LeaveDetailsRepository ldRepo;
	@Resource
	private LeaveTypeRepository ltRepo;
	@Resource
	private DesignationRepository dRepo;
	@Resource
	private LeaveBalanceRepository lbRepo;
	
	public LeaveDetailsBean createBean(Integer leavetypeid, Integer designationid) {
		LeaveDetailsBean ldbean = new LeaveDetailsBean();
		LeaveType leavetype = ltRepo.findByTypeId(leavetypeid);	
		Designation designation = dRepo.findById(designationid).orElse(null);
		LeaveDetails leavedetail = ldRepo.findByLeaveTypeAndDesignation(leavetype, designation);
		ldbean.setDesignationId(designationid);
		ldbean.setLeaveTypeId(leavetypeid);
		ldbean.setMaximumLeave(leavedetail.getMaximumLeave());
		ldbean.setDesignationName(designation.getDesignationName());
		ldbean.setLeaveTypeName(leavetype.getTypeName());
		return ldbean;
	}
	
	public void saveAndPopulate(LeaveType leavetype) {
		ltRepo.save(leavetype);
		LeaveType newlycreatedleave = ltRepo.findTopByOrderByTypeIdDesc();
		List<Designation> alldesignations = dRepo.findAll();
		for (Iterator iterator = alldesignations.iterator(); iterator.hasNext();) {
			Designation designation = (Designation) iterator.next();
			int designationid = designation.getId();
			ldRepo.addNewLeaveType(designationid, newlycreatedleave.getTypeId());
		}
	}
	
	public void deleteType(Integer leavetypeid) {
		lbRepo.deleteBalanceForDeletedLeaveType(leavetypeid);
		ldRepo.deleteDetailsForDeletedLeaveType(leavetypeid);
		ltRepo.deleteById(leavetypeid);
	}
	
	public void updateSaveHoliday(LeaveDetailsBean ldbean) {
		ldRepo.updateAndSave(ldbean.getLeaveTypeId(), ldbean.getDesignationId(), ldbean.getMaximumLeave());
	}
	
	public List<LeaveType> getLeaveTypeList() {
		return ltRepo.findAll();
	}
}
