package sg.nus.iss.mvc.controller;

import java.util.Optional;

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
public class LoginController {

	private StaffRepository staffRepo;

	@Autowired
	public void setStaffRepo(StaffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}

	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("staff", new Staff());
		return "loginForm";
	}

	@RequestMapping(path = "main", method = RequestMethod.POST)
	public String checkid(Staff staff) {
		Staff user = staffRepo.findByStaffname(staff.getStaff_name());
		if (user.getPassword().equalsIgnoreCase(staff.getPassword())) {
			return "Staffs";
		} else {
			return "Leave_ApplicationForm";
		}
	}
}
