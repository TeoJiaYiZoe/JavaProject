package sg.nus.iss.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.nus.iss.mvc.model.Leave_Type;
import sg.nus.iss.mvc.model.Leave_Application;
import sg.nus.iss.mvc.repo.Leave_TypeRepository;
import sg.nus.iss.mvc.repo.Leave_ApplicationRepository;

@Controller
public class Leave_ApplicationController {

	private Leave_ApplicationRepository leave_applicationRepo;
	private Leave_TypeRepository leave_typeRepo;

	@Autowired
	public void seLeave_ApplicationRepo(Leave_ApplicationRepository leave_applicationRepo) {
		this.leave_applicationRepo = leave_applicationRepo;
	}
	
	@Autowired
	public void setLeave_typeRepo(Leave_TypeRepository leave_typeRepo) {
		this.leave_typeRepo = leave_typeRepo;
	}


	@RequestMapping(path = "leave_application/submit", method = RequestMethod.GET)
	public String createLeave_Application(Model model) {
		model.addAttribute("leave_application", new Leave_Application());
		List<Leave_Type> leave_types = leave_typeRepo.findAll();
		model.addAttribute("leave_types", leave_types);
		return "Leave_ApplicationForm";
	}
	
	@RequestMapping(path = "leave_applications", method = RequestMethod.POST)
	public String saveStaff(Leave_Application leave_application) {
		leave_applicationRepo.save(leave_application);
		return "Leave_Applications";
	}
}
