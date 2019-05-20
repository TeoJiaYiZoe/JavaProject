package sg.nus.iss.mvc.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Leave_Application {

	@Id
	private int leave_id;
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_date;
	private String reason;
	private String work_instruction;
	private String contact;
	private int leavetype_id;
	private String status;
	private String comment;
	public Leave_Application() {
		super();
		this.status = "APPLIED";
		// TODO Auto-generated constructor stub
	}
	public Leave_Application( Date start_date, Date end_date, String reason, String work_instruction,
			String contact_details, int leavetype_id, String status, String comment) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.reason = reason;
		this.work_instruction = work_instruction;
		this.contact = contact_details;
		this.leavetype_id = leavetype_id;
		this.status = status;
		this.comment = comment;
	}
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWork_instruction() {
		return work_instruction;
	}
	public void setWork_instruction(String work_instruction) {
		this.work_instruction = work_instruction;
	}
	public String getContact_details() {
		return contact;
	}
	public void setContact_details(String contact_details) {
		this.contact = contact_details;
	}
	public int getLeavetype_id() {
		return leavetype_id;
	}
	public void setLeavetype_id(int leavetype_id) {
		this.leavetype_id = leavetype_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
