package sg.nus.iss.mvc.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.mvc.model.Holiday;
import sg.nus.iss.mvc.repo.HolidayRepository;

@Service
public class HolidayServiceImpl implements HolidayService{

	@Resource
	private HolidayRepository holidayRepo;
	
	@Override
	@Transactional
	public int findLeaveDaysWithoutHoliday(LocalDate start, LocalDate end) {
		Period p = Period.between(start, end);
		int res = p.getDays()+1;
		LocalDate tempdate = LocalDate.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
		//Calendar calendar = Calendar.getInstance();
		List<LocalDate> holidays = holidayRepo.findAllDate();
		
		while(!tempdate.isAfter(end)) {
			
			if(holidays.contains(tempdate)||tempdate.getDayOfWeek().equals(DayOfWeek.SUNDAY)||tempdate.getDayOfWeek().equals(DayOfWeek.SATURDAY))
			{
				res--;
			}
			
			tempdate = tempdate.plusDays(1);
		}
		//for(Holiday h: holidays) {
		//	if(h.getDate().isAfter(start) && h.getDate().isBefore(end)) {
		//		res--;
		//	}
		//	else if(h.getDate().isEqual(start) || h.getDate().isEqual(end)){
		//		res--;
		//	}
		//}
		return res;
	}
}
