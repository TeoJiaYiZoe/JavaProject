package sg.nus.iss.mvc.repo;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.mvc.model.LeaveApplication;
import sg.nus.iss.mvc.model.Staff;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Integer>{

	public List<LeaveApplication> findLeaveByStaff(Staff Staff);
	
	@Transactional
	@Query("SELECT la FROM Staff s, LeaveApplication la WHERE s.staffId = la.staff.staffId AND s.staff = ?1 AND la.status='APPLIED'")
	List<LeaveApplication> findSubordinateByBossId(int bossId);
	
	@Transactional
	@Query("SELECT la FROM Staff s, LeaveApplication la WHERE s.staffId = la.staff.staffId AND s.staff = ?1")
	public List<LeaveApplication> findEmployeeByid(int id);
	
	public LeaveApplication findByid(int id);
	
	@Query(value= "select u from LeaveApplication u where u.endDate >= :startdate  and  u.startDate <= :enddate and"
			+ " u.staff in (select s.id from Staff s where s.staff = :staffid)")
	List<LeaveApplication> checkOverlapLeave (@Param("startdate") LocalDate startdate, @Param("enddate") LocalDate enddate,
			@Param("staffid") int staffid);
}
