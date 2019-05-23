package sg.nus.iss.mvc.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.model.leaveApplication;
public interface leaveApplicationRepository extends JpaRepository<leaveApplication,Integer>{

	public List<leaveApplication> findLeaveByStaff(Staff staff);
}
