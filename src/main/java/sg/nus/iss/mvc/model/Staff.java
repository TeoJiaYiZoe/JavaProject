package sg.nus.iss.mvc.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

@Entity
public class Staff {

	@Id
	private int staff_id;
	@NotEmpty
	@Size(min = 2, max =50)
	@Column(name="staff_name")
	private String staffname;
	@NotEmpty
	private int designation_id;
	@Length(min = 3)
	private String password;
	@OneToMany(targetEntity = Leave_Application.class, mappedBy = "staff")
	private Collection<Leave_Application> leave_application;
	//CONSTRUCTORS
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Staff(String sataff_name, int designation_id, String password) {
		super();
		this.staffname = sataff_name;
		this.designation_id = designation_id;
		this.password = password;
	}
	//GETTERS & SETTERS
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staffname;
	}
	public void setStaff_name(String staff_name) {
		this.staffname = staff_name;
	}
	public int getDesignation_id() {
		return designation_id;
	}
	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
