package sg.nus.iss.mvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="designation_leave")
@IdClass(LeaveDetailsId.class)
public class LeaveDetails implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="leavetype_id")
	private LeaveType LeaveType;
	@Id
	@ManyToOne
	@JoinColumn(name="designation_id")
	private Designation Designation;
	
	@Column(name="maximum_leave")
	private int maximumLeave;

	
	
	public LeaveDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Designation == null) ? 0 : Designation.hashCode());
		result = prime * result + ((LeaveType == null) ? 0 : LeaveType.hashCode());
		result = prime * result + maximumLeave;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeaveDetails other = (LeaveDetails) obj;
		if (Designation == null) {
			if (other.Designation != null)
				return false;
		} else if (!Designation.equals(other.Designation))
			return false;
		if (LeaveType == null) {
			if (other.LeaveType != null)
				return false;
		} else if (!LeaveType.equals(other.LeaveType))
			return false;
		if (maximumLeave != other.maximumLeave)
			return false;
		return true;
	}


	public LeaveType getLeaveType() {
		return LeaveType;
	}


	public void setLeaveType(LeaveType LeaveType) {
		this.LeaveType = LeaveType;
	}


	public Designation getDesignation() {
		return Designation;
	}


	public void setDesignation(Designation Designation) {
		this.Designation = Designation;
	}


	public int getMaximumLeave() {
		return maximumLeave;
	}

	public void setMaximumLeave(int maximumLeave) {
		this.maximumLeave = maximumLeave;
	}
	
	
	
}
