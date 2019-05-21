package sg.nus.iss.mvc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.model.User;
import sg.nus.iss.mvc.repo.StaffRepository;

@Controller
@SessionAttributes("user")
public class LoginController {

	private StaffRepository staffRepo;

	@Autowired
	public void setStaffRepo(StaffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}

	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		return "loginForm";
	}

	@RequestMapping(path = "homepage", method = RequestMethod.POST)
	public String checkid(User user, Model model) {
		Staff staff = staffRepo.findByStaffName(user.getUsername());
		if (staff.getPassword().equalsIgnoreCase(user.getPassword())) {
			model.addAttribute("user", staff);
			return "homepage";
		} else {
			return "loginForm";
		}
	}
}
