package sg.nus.iss.mvc.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.nus.iss.mvc.javabean.LeaveDetailsBean;
import sg.nus.iss.mvc.model.Designation;
import sg.nus.iss.mvc.model.LeaveDetails;
import sg.nus.iss.mvc.model.LeaveType;
import sg.nus.iss.mvc.repo.DesignationRepository;
import sg.nus.iss.mvc.repo.LeaveDetailsRepository;
import sg.nus.iss.mvc.repo.LeaveTypeRepository;

@Controller
public class LeaveTypeController {

	private LeaveDetailsRepository ldRepo;
	private LeaveTypeRepository ltRepo;
	private DesignationRepository dRepo;
	
	@Autowired
	public void setLdRepo(LeaveDetailsRepository ldRepo) {
		this.ldRepo = ldRepo;
	}
	@Autowired
	public void setLtRepo(LeaveTypeRepository ltRepo) {
		this.ltRepo = ltRepo;
	}
	@Autowired
	public void setdRepo(DesignationRepository dRepo) {
		this.dRepo = dRepo;
	}

	
	//Update Leave max value
	@RequestMapping(path = "/admin/leavetype/edit/{ltid}/{deid}", method = RequestMethod.GET)
	public String displayUpdateLeaveMainpage(Model model, @PathVariable("ltid") Integer leavetypeid, @PathVariable("deid") Integer designationid) {
		LeaveType leavetype = ltRepo.findByTypeId(leavetypeid);	
		Designation designation = dRepo.findById(designationid).orElse(null);
		LeaveDetails leavedetail = ldRepo.findByLeaveTypeAndDesignation(leavetype, designation);
		LeaveDetailsBean ldbean = new LeaveDetailsBean();
		ldbean.setDesignationId(designationid);
		ldbean.setLeaveTypeId(leavetypeid);
		ldbean.setMaximumLeave(leavedetail.getMaximumLeave());
		ldbean.setDesignationName(designation.getDesignationName());
		ldbean.setLeaveTypeName(leavetype.getTypeName());
		model.addAttribute("leavedetail", ldbean);
		return "leavetype-edit";
	}
	 
	@RequestMapping(path = "/updateleavetype", method = RequestMethod.POST)
	public String updateHoliday(LeaveDetailsBean ldbean) {

		ldRepo.updateAndSave(ldbean.getLeaveTypeId(), ldbean.getDesignationId(), ldbean.getMaximumLeave());
		//ldRepo.updateAndSave(1, 1, ldbean.getMaximumLeave());
		
		return "redirect:/admin/leavetype-elfie";
		
	}
	//Delete Leave Types
	@RequestMapping(path="/admin/leavetype/delete", method = RequestMethod.GET)
	public ModelAndView displayDeleteLeaveMainpage() {
		ModelAndView mav = new ModelAndView("leavetype-delete");
		List<LeaveType> ltlist = ltRepo.findAll();
	
		mav.addObject("ltlist", ltlist);
		
		return mav;
	}
	
	@RequestMapping(path="/admin/leavetype/delete/{id}", method = RequestMethod.GET)
	public String deleteLeaveType(@PathVariable("id") Integer leavetypeid) {
		///code to delete from LeaveBalance, from LeaveDetails, and finally from LeaveType 
		return "redirect:/admin/leavetype-elfie";
	}
	
	//Add Leave Type code
	@RequestMapping(path="/admin/leavetype/add", method = RequestMethod.GET)
	public ModelAndView displayAddLeaveMainpage() {
		ModelAndView mav = new ModelAndView("leavetype-add");
		LeaveType leavetype = new LeaveType();
		
	
		mav.addObject("leavetype", leavetype);
		
		return mav;
	}
	
	@RequestMapping(path="/addLeaveType", method = RequestMethod.POST)
	public ModelAndView AddLeaveType(LeaveType leavetype) {
		ModelAndView mav = new ModelAndView("redirect:/admin/leavetype-elfie");
		ltRepo.save(leavetype);
		LeaveType newlycreatedleave = ltRepo.findTopByOrderByTypeIdDesc();
		List<Designation> alldesignations = dRepo.findAll();
		for (Iterator iterator = alldesignations.iterator(); iterator.hasNext();) {
			Designation designation = (Designation) iterator.next();
			int designationid = designation.getId();
			ldRepo.addNewLeaveType(designationid, newlycreatedleave.getTypeId());
		}
		return mav;
	}
}
