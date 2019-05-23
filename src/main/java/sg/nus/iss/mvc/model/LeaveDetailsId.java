package sg.nus.iss.mvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class LeaveDetailsId implements Serializable {


	private LeaveType LeaveType;

	private Designation Designation;
	
	public LeaveDetailsId() {
		super();
		
	}

	public LeaveDetailsId(sg.nus.iss.mvc.model.LeaveType LeaveType, Designation Designation) {
		super();
		this.LeaveType = LeaveType;
		this.Designation = Designation;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Designation == null) ? 0 : Designation.hashCode());
		result = prime * result + ((LeaveType == null) ? 0 : LeaveType.hashCode());
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
		LeaveDetailsId other = (LeaveDetailsId) obj;
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
		return true;
	}



	
	
}
