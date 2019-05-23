package sg.nus.iss.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sg.nus.iss.mvc.repo.HolidayRepository;

@Controller
public class HolidayController {

	private HolidayRepository holidayRepo;

	@Autowired
	public void setHolidayRepo(HolidayRepository holidayRepo) {
		this.holidayRepo = holidayRepo;
	}
	
}
