package sg.nus.iss.mvc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mvc.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff,String>{

	public Staff findByStaffName(String staffname);
	
	@Transactional
	@Query("SELECT s FROM Staff s where s.staff = ?1")
	public List<Staff> findHistoryByid(int id);
}
