package sg.nus.iss.mvc.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="holiday_list")
public class holiday {

	@Id
	@Column(name="id")
	private int holidayId;
	private int year;
	@Column(name="Name")
	private String holidayName;
	@Column(name="date")
	private LocalDate date;
	//@Column(name="end_date")
	//private LocalDate holidayEnd;
	public holiday() {
		super();
		// TODO Auto-generated constructor stub
	}
	public holiday(int year, String holidayName, LocalDate holidayStart) {
		super();
		this.year = year;
		this.holidayName = holidayName;
		this.date = holidayStart;
		//this.holidayEnd = holidayEnd;
	}
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
