package sg.nus.iss.mvc.service;

import java.time.LocalDate;

public interface holidayService {

	int findLeaveDaysWithoutHoliday(LocalDate start, LocalDate end);
}
