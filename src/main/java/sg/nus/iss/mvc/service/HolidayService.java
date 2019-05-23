package sg.nus.iss.mvc.service;

import java.time.LocalDate;

public interface HolidayService {

	int findLeaveDaysWithoutHoliday(LocalDate start, LocalDate end);
}
