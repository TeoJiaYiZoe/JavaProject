package sg.nus.iss.mvc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mvc.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff,String>{

	public Staff findByStaffname(String staffname);
}
