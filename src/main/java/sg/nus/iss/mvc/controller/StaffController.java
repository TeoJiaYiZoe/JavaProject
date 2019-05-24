package sg.nus.iss.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.repo.StaffRepository;

@Controller
public class StaffController {

	private StaffRepository staffRepo;

	@Autowired
	public void setStaffRepo(StaffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}
	
	@RequestMapping(path = "Staff/add", method = RequestMethod.GET)
	public String createStaff(Model model) {
		model.addAttribute("Staff", new Staff());
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
