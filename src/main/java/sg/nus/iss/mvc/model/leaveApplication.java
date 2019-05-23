package sg.nus.iss.mvc.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Column(name="ID")
	private int id;
	//@Column(name="STAFF_ID")
	//private int staffId;
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	@Column(name="start_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@Column(name="end_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	private String reason;
	@Column(name="work_instruction")
	private String workInstruction;
	//private String contact;
	private int contact;
	//@Column(name="LEAVETYPE_ID")
	//private int leaveTypeId;
	@ManyToOne
	@JoinColumn(name="LEAVETYPE_ID")
	private leaveType leavetype;
	private String status;
	private String comment;
	public leaveApplication() {
		super();
		this.status = "APPLIED";
		// TODO Auto-generated constructor stub
	}
	public leaveApplication(Staff staff, LocalDate startDate, LocalDate endDate, String reason, String workInstruction,
			int contact, leaveType leavetype, String status, String comment) {
		super();
		this.staff = staff;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.workInstruction = workInstruction;
		this.contact = contact;
		this.leavetype = leavetype;
		this.status = "APPLIED";
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
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
	
	public leaveType getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(leaveType leavetype) {
		this.leavetype = leavetype;
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
	/*
	 * public int getStaffId() { return staffId; } public void setStaffId(int
	 * staffId) { this.staffId = staffId; }
	 */
	/*
	 * public int getLeaveTypeId() { return leaveTypeId; } public void
	 * setLeaveTypeId(int leaveTypeId) { this.leaveTypeId = leaveTypeId; }
	 */
	
	
}
