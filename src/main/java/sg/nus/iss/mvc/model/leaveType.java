package sg.nus.iss.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="leave_type")
public class leaveType {

	@Id
	@Column(name="id")
	private int typeId;
	@Column(name="name")
	private String typeName;
	public leaveType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public leaveType(String type_name) {
		super();
		this.typeName = type_name;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
