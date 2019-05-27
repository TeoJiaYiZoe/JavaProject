package sg.nus.iss.mvc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.nus.iss.mvc.model.LeaveApplication;
import sg.nus.iss.mvc.model.LeaveBalance;
import sg.nus.iss.mvc.model.LeaveType;
import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.repo.LeaveApplicationRepository;
import sg.nus.iss.mvc.repo.LeaveTypeRepository;
import sg.nus.iss.mvc.service.HolidayService;
import sg.nus.iss.mvc.service.LeaveApplicationService;
import sg.nus.iss.mvc.service.LeaveBalanceService;
import sg.nus.iss.mvc.validator.LeaveApplicationValidator;

@Controller
@SessionAttributes("User")
public class EmployeeController {

	private LeaveApplicationRepository leave_applicationRepo;
	private LeaveTypeRepository leave_typeRepo;
	private LeaveBalanceService leaveBalanceSer;
	@Autowired
	private HolidayService holidaySer;
	@Autowired
	private LeaveApplicationService leaveApplicationSer;

	@Autowired
	private LeaveApplicationValidator laValidator;

	@InitBinder("leave_application")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(laValidator);
	}

	@Autowired
	public void setLeaveApplicationRepo(LeaveApplicationRepository leave_applicationRepo) {
		this.leave_applicationRepo = leave_applicationRepo;
	}

	@Autowired
	public void setLeaveTypeRepo(LeaveTypeRepository leave_typeRepo) {
		this.leave_typeRepo = leave_typeRepo;
	}

	@Autowired
	public void setLeaveBalanceSer(LeaveBalanceService leaveBalanceSer) {
		this.leaveBalanceSer = leaveBalanceSer;
	}

	@RequestMapping(path = "/leave/apply", method = RequestMethod.GET)
	public String createLeaveApplication(Model model,@ModelAttribute("User") Staff staff) {
		LeaveApplication la = new LeaveApplication();
		la.setStaff(staff);
		model.addAttribute("leave_application",la);
		List<LeaveType> leave_types = leave_typeRepo.findAll();
		model.addAttribute("leave_types", leave_types);
		return "leaveApplicationForm";
	}

	@RequestMapping(path = "/leaveApplication/submit", method = RequestMethod.POST)
	public String saveApplication(Model model,
			@Valid @ModelAttribute("leave_application") LeaveApplication leave_application, BindingResult bindingResult,
			@ModelAttribute("User") Staff staff) {
		leave_application.setStaff(staff);
		List<LeaveType> leave_types = leave_typeRepo.findAll();
		model.addAttribute("leave_types", leave_types);
		if (bindingResult.hasErrors()) {
			return "leaveApplicationForm";
		}

		LeaveBalance lb = leaveBalanceSer.findByStaffAndLeavetype(staff, leave_application.getLeavetype());
		int balance = lb.getBalance();
		int leavedays = holidaySer.findLeaveDaysWithoutHoliday(leave_application.getStartDate(),
				leave_application.getEndDate());
		if (leavedays <= balance) {
			int bal = balance - leavedays;
			leave_applicationRepo.save(leave_application);
			leaveBalanceSer.saveBalanceByStaffAndType(leave_application.getLeavetype(), bal, staff);
		}
		return "redirect:/leave";
	}
	
	@RequestMapping(path = "/leave/balance")
    public String viewLeaveBalance(Model model, @ModelAttribute("User") Staff staff) {
		List<LeaveBalance> listBalanceLeave = leaveBalanceSer.findByStaff(staff);
		model.addAttribute("listBalanceLeave", listBalanceLeave);
        return "leaveBalance";
    }
	
	@RequestMapping(path = "/leave")
    public String viewLeave(Model model, @ModelAttribute("User") Optional<Staff> staff) {
		List<LeaveApplication> listLeave = leave_applicationRepo.findLeaveByStaff(staff);
		model.addAttribute("listLeave", listLeave);
        return "leave";
    }
	
	@RequestMapping(path = "/leave/viewDetails/{id}", method = RequestMethod.GET)
    public String viewLeaveDetails(Model model, @PathVariable(value = "id") Integer id) {   	
    	LeaveApplication leave = leave_applicationRepo.findById(id).orElse(null);
    	System.out.println(leave);
        model.addAttribute("leaveDetails", leave);
        return "details";
    }
	
    @RequestMapping(path = "/leave/edit/{id}", method = RequestMethod.GET)
    public String editLeave(Model model, @PathVariable(value = "id") Integer id) {   	
    	LeaveApplication leaveEdit = leave_applicationRepo.findById(id).orElse(null);
    	System.out.println(leaveEdit);
        model.addAttribute("leaveEdit", leaveEdit);
        return "edit";
    }
    
    @RequestMapping(path = "/leave/save", method = RequestMethod.POST)
    public String saveLeaveDetails(@ModelAttribute("leaveEdit") @Valid LeaveApplication leaveAppl, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return "edit";
    	}
        leave_applicationRepo.save(leaveAppl);
        return "redirect:/leave";
    }
    
    @RequestMapping(path = "/leave/delete/{id}", method = RequestMethod.GET)
    public String deleteLeave(@PathVariable(name = "id") Integer id) {
        LeaveApplication la = leave_applicationRepo.findById(id).orElse(null);
        la.setStatus("DELETED");
        leave_applicationRepo.save(la);
        return "redirect:/leave";
    }
    
    @RequestMapping(path = "/leave/cancel/{id}", method = RequestMethod.GET)
    public String cancelApprovedLeave(@PathVariable(name = "id") Integer id) {
        LeaveApplication la = leave_applicationRepo.findById(id).orElse(null);
        la.setStatus("CANCELLED");
        leave_applicationRepo.save(la);
        return "redirect:/leave";
    }
}
