package sg.nus.iss.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Leave_Type {

	@Id
	private int type_id;
	private String type_name;
	public Leave_Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Leave_Type(String type_name) {
		super();
		this.type_name = type_name;
	}
	public int getLeavetype_id() {
		return type_id;
	}
	public void setLeavetype_id(int leavetype_id) {
		this.type_id = leavetype_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
}
