package sg.nus.iss.mvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="leave_balance")
@IdClass(leaveBalanceKey.class)
public class leaveBalance implements Serializable{

	@Id
	@ManyToOne
	@JoinColumn(name="leavetype_id")
	private leaveType leavetype;
	@Id
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;
	
	@Column(name="leave_available")
	private int balance;
	public leaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public leaveBalance(leaveType leavetype, Staff staff, int balance) {
		super();
		this.leavetype = leavetype;
		this.staff = staff;
		this.balance = balance;
	}
	public leaveType getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(leaveType leavetype) {
		this.leavetype = leavetype;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
