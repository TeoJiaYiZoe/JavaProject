package sg.nus.iss.mvc.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

@Entity
public class Staff {

	@Id
	@Column(name="id")
	private int staffId;
	@NotEmpty
	@Size(min = 2, max =50)
	@Column(name="name")
	private String staffName;
	@Column(name="designation_id")
	private int designationId;
	
	@Length(min = 3)
	private String password;
	@OneToMany(targetEntity = leaveApplication.class, mappedBy = "staff")
	private Collection<leaveApplication> leave_application;
	
	//CONSTRUCTORS
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Staff(String sataff_name, int designation_id, String password) {
		super();
		this.staffName = sataff_name;
		this.designationId = designation_id;
		this.password = password;
	}
	//GETTERS & SETTERS

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<leaveApplication> getLeave_application() {
		return leave_application;
	}

	public void setLeave_application(Collection<leaveApplication> leave_application) {
		this.leave_application = leave_application;
	}
	
	
}
