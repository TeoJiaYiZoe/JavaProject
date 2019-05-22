package sg.nus.iss.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.repo.staffRepository;

@Controller
public class StaffController {

	private staffRepository staffRepo;

	@Autowired
	public void setStaffRepo(staffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}
	
	@RequestMapping(path = "staff/add", method = RequestMethod.GET)
	public String createStaff(Model model) {
		model.addAttribute("staff", new Staff());
		return "StaffForm";
	}
	
	@RequestMapping(path = "staffs", method = RequestMethod.POST)
	public String saveStaff(@Valid Staff staff, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "StaffForm";
		}
		staffRepo.save(staff);
		return "Staffs";
	}
}
