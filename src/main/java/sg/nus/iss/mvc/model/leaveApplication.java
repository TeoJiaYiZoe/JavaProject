package sg.nus.iss.mvc.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="leave_application")
public class leaveApplication {

	@Id
	@Column(name="id")
	private int leaveId;
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	@Column(name="start_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@Column(name="end_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private String reason;
	@Column(name="work_instruction")
	private String workInstruction;
	//private String contact;
	private int contact;
	
	@Column(name="leavetype_id")
	private int leavetypeId;
	private String status;
	private String comment;
	public leaveApplication() {
		super();
		this.status = "APPLIED";
		// TODO Auto-generated constructor stub
	}
	public leaveApplication(Staff staff, Date startDate, Date endDate, String reason, String workInstruction,
			int contact, int leavetypeId, String status, String comment) {
		super();
		this.staff = staff;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.workInstruction = workInstruction;
		this.contact = contact;
		this.leavetypeId = leavetypeId;
		this.status = "APPLIED";
		this.comment = comment;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWorkInstruction() {
		return workInstruction;
	}
	public void setWorkInstruction(String workInstruction) {
		this.workInstruction = workInstruction;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public int getLeavetypeId() {
		return leavetypeId;
	}
	public void setLeavetypeId(int leavetypeId) {
		this.leavetypeId = leavetypeId;
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
