package sg.nus.iss.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sg.nus.iss.mvc.repo.holidayRepository;

@Controller
public class holidayController {

	private holidayRepository holidayRepo;

	@Autowired
	public void setHolidayRepo(holidayRepository holidayRepo) {
		this.holidayRepo = holidayRepo;
	}
	
}
