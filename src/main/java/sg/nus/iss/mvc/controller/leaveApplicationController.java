package sg.nus.iss.mvc.controller;

import java.util.List;

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

import sg.nus.iss.mvc.model.leaveType;
import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.model.leaveApplication;
import sg.nus.iss.mvc.model.leaveBalance;
import sg.nus.iss.mvc.repo.leaveTypeRepository;
import sg.nus.iss.mvc.service.holidayService;
import sg.nus.iss.mvc.service.leaveBalanceService;
import sg.nus.iss.mvc.repo.holidayRepository;
import sg.nus.iss.mvc.repo.leaveApplicationRepository;
import sg.nus.iss.mvc.repo.leaveBalanceRepository;

@Controller
@SessionAttributes("user")
public class leaveApplicationController {

	private leaveApplicationRepository leave_applicationRepo;
	private leaveTypeRepository leave_typeRepo;
	private leaveBalanceService leaveBalanceSer;
	@Autowired
	private holidayService holidaySer;

	@Autowired
	public void setLeaveApplicationRepo(leaveApplicationRepository leave_applicationRepo) {
		this.leave_applicationRepo = leave_applicationRepo;
	}

	@Autowired
	public void setLeaveTypeRepo(leaveTypeRepository leave_typeRepo) {
		this.leave_typeRepo = leave_typeRepo;
	}

	@Autowired
	public void setLeaveBalanceSer(leaveBalanceService leaveBalanceSer) {
		this.leaveBalanceSer = leaveBalanceSer;
	}

	@RequestMapping(path = "leave_application/submit", method = RequestMethod.GET)
	public String createLeaveApplication(Model model) {
		model.addAttribute("leave_application", new leaveApplication());
		List<leaveType> leave_types = leave_typeRepo.findAll();
		model.addAttribute("leave_types", leave_types);
		return "leaveApplicationForm";
	}

	@RequestMapping(path = "leave_applications", method = RequestMethod.POST)
	public String saveApplication(leaveApplication leave_application,
			@ModelAttribute("user") Staff staff) {
		leave_application.setStaff(staff);
		//System.console().printf(staff.toString());
		leaveBalance lb = leaveBalanceSer.findByStaffAndLeavetype(staff, leave_application.getLeavetype());
		//System.console().printf(lb.toString());
		int balance = lb.getBalance();
		int leavedays = holidaySer.findLeaveDaysWithoutHoliday(leave_application.getStartDate(),
				leave_application.getEndDate());
		if (leavedays <= balance) {
			int bal = balance-leavedays;
			leave_applicationRepo.save(leave_application);
			leaveBalanceSer.saveBalanceByStaffAndType(leave_application.getLeavetype(), bal, staff);
			return "HomePage";
		} else {
			return "Staffs";
		}
	}
	
	@RequestMapping(path = "/leave/balance")
    public String viewLeaveBalance(Model model, @ModelAttribute("user") Staff staff) {
		List<leaveBalance> listBalanceLeave = leaveBalanceSer.findByStaff(staff);
		model.addAttribute("listBalanceLeave", listBalanceLeave);
        return "leaveBalance";
    }
	
	@RequestMapping(path = "/leave")
    public String viewLeave(Model model, @ModelAttribute("user") Staff staff) {
		List<leaveApplication> listLeave = leave_applicationRepo.findLeaveByStaff(staff);
		model.addAttribute("listLeave", listLeave);
        return "leave";
    }
	
	@RequestMapping(path = "/leave/viewDetails/{id}", method = RequestMethod.GET)
    public String viewLeaveDetails(Model model, @PathVariable(value = "id") Integer id) {   	
    	leaveApplication leave = leave_applicationRepo.findById(id).orElse(null);
    	System.out.println(leave);
        model.addAttribute("leaveDetails", leave);
        return "details";
    }
	
    @RequestMapping(path = "/leave/edit/{id}", method = RequestMethod.GET)
    public String editLeave(Model model, @PathVariable(value = "id") Integer id) {   	
    	leaveApplication leaveEdit = leave_applicationRepo.findById(id).orElse(null);
    	System.out.println(leaveEdit);
        model.addAttribute("leaveEdit", leaveEdit);
        return "edit";
    }
    
    @RequestMapping(path = "/leave", method = RequestMethod.POST)
    public String saveLeaveDetails(leaveApplication leaveAppl) {
        leave_applicationRepo.save(leaveAppl);
        return "redirect:/leave";
    }
    
    @RequestMapping(path = "/leave/delete/{id}", method = RequestMethod.GET)
    public String deleteLeave(@PathVariable(name = "id") Integer id) {
        leaveApplication la = leave_applicationRepo.findById(id).orElse(null);
        la.setStatus("DELETED");
        leave_applicationRepo.save(la);
        return "redirect:/leave";
    }
    
    @RequestMapping(path = "/leave/cancel/{id}", method = RequestMethod.GET)
    public String cancelApprovedLeave(@PathVariable(name = "id") Integer id) {
        leaveApplication la = leave_applicationRepo.findById(id).orElse(null);
        la.setStatus("CANCELLED");
        leave_applicationRepo.save(la);
        return "redirect:/leave";
    }
}
