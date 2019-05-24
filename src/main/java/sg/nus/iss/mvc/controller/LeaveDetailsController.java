package sg.nus.iss.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.nus.iss.mvc.model.LeaveDetails;
import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.repo.DesignationRepository;
import sg.nus.iss.mvc.repo.LeaveDetailsRepository;
import sg.nus.iss.mvc.repo.LeaveTypeRepository;

@Controller
@SessionAttributes("User")
public class LeaveDetailsController {
	
		private LeaveDetailsRepository ldRepo;
		private LeaveTypeRepository ltRepo;
		private DesignationRepository desRepo;

		@Autowired
		public void setLdRepo(LeaveDetailsRepository ldRepo) {
			this.ldRepo = ldRepo;
		}
		@Autowired
		public void setLtRepo(LeaveTypeRepository ltRepo) {
			this.ltRepo = ltRepo;
		}
		@Autowired
		public void setDesRepo(DesignationRepository desRepo) {
			this.desRepo = desRepo;
		}


		
		@RequestMapping(path="/admin/leavetype-elfie")
		public String showLeaveTypeMainpage(Model model) {
			
			long leaveD = ldRepo.count();
			List<LeaveDetails> ldlist = ldRepo.findAll();
			
			model.addAttribute("numberofrows", leaveD);
			model.addAttribute("ldlist", ldlist);
			
			return "leavetype-elfie";
		}
	
}
