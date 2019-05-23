package sg.nus.iss.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		leaveBalance lb = leaveBalanceSer.findByStaffAndLeavetype(staff, leave_application.getLeavetype());
		int balance = lb.getBalance();
		int leavedays = holidaySer.findLeaveDaysWithoutHoliday(leave_application.getStartDate(),
				leave_application.getEndDate());
		if (leavedays <= balance) {
			int bal = balance-leavedays;
			leave_applicationRepo.save(leave_application);
			leaveBalanceSer.saveBalanceByStaffAndType(leave_application.getLeavetype(), bal, staff);
			return "leaveApplications";
		} else {
			return "Staffs";
		}
	}
}
