package sg.nus.iss.mvc.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.nus.iss.mvc.model.LeaveApplication;
import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.repo.LeaveApplicationRepository;
import sg.nus.iss.mvc.repo.StaffRepository;

@Controller
@SessionAttributes("User")
public class ManagerController {

	private LeaveApplicationRepository leave_applicationRepo;
	private StaffRepository staff_typeRepo;
	
	@Autowired
	public void setLeaveApplicationRepo(LeaveApplicationRepository leave_applicationRepo) {
		this.leave_applicationRepo = leave_applicationRepo;
	}
	@Autowired
	public void setStaff_typeRepo(StaffRepository staff_typeRepo) {
		this.staff_typeRepo = staff_typeRepo;
	}
	
	 @RequestMapping(path = "/leaverequest/submit", method = RequestMethod.POST)
	    public String save(@ModelAttribute("leave_application") @Valid LeaveApplication leave_application, BindingResult bindingResult) {
	    	if (bindingResult.hasErrors()) {
	    		System.out.println(bindingResult.getFieldError("comment"));
	            return "Editform";
	        }
	    	
	    	leave_applicationRepo.save(leave_application);
	        return "redirect:/leaverequest";
	    }
	    @RequestMapping(path = "/leaverequest", method = RequestMethod.GET)
	    public String getAll(Model model,@ModelAttribute("User") Staff staff) {
	    	List<LeaveApplication> listb = leave_applicationRepo.findSubordinateByBossId(staff.getStaffId());
	        model.addAttribute("leave_applications", listb);
	        return "Request";
	    } 
	    @RequestMapping(path = "/leaverequest/edit/{id}", method = RequestMethod.GET)
	    public String edit(Model model, @PathVariable(value = "id") Integer id,@ModelAttribute("User") Staff staff) {   	
	    	LeaveApplication leave_application= leave_applicationRepo.findByid(id);
	        model.addAttribute("leave_application", leave_application);
			LocalDate startdate = leave_application.getStartDate(); 
			LocalDate enddate =   leave_application.getEndDate();
			List<LeaveApplication> leavelist = leave_applicationRepo.checkOverlapLeave(startdate, enddate,staff.getStaffId());
			model.addAttribute("leavefor5", leavelist);
	
	        return "Editform";
	    }
	    @RequestMapping(path = "/leavehistory", method = RequestMethod.GET)
	    public String getHistoryOfEmployee(Model model,@ModelAttribute("User") Staff staff) {
	    	List<Staff> s =staff_typeRepo.findHistoryByid(staff.getStaffId());
	    	model.addAttribute("listOfSubordinates", s);   //rename
	        return "EmployeeHistory";
	    } 
	    @RequestMapping(path = "/leavehistory/view/{id}", method = RequestMethod.GET)
	    public String viewleavehistory(Model model, @PathVariable(value = "id") Integer id) {
	    	Optional<Staff> s = staff_typeRepo.findById(id); 
	        List<LeaveApplication> leaveHistory = leave_applicationRepo.findLeaveByStaff(s);
	        model.addAttribute("leaveHistory", leaveHistory);

	        return "Eleave";
	    }
}