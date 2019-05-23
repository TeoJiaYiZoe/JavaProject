package sg.nus.iss.mvc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.mvc.model.Staff;
import sg.nus.iss.mvc.model.leaveBalance;
import sg.nus.iss.mvc.model.leaveBalanceKey;
import sg.nus.iss.mvc.model.leaveType;

@Repository
public interface leaveBalanceRepository extends JpaRepository<leaveBalance,leaveBalanceKey>{

	leaveBalance findByStaffAndLeavetype(Staff staff, leaveType lt);
	List<leaveBalance> findByStaff(Staff staff);
	
	@Transactional
	@Modifying
	@Query("update leaveBalance lb set lb.balance = :bal where lb.staff.staffId = :staffid and lb.leavetype.typeId = :leavetypeid")
	void savebalanceByStaffAndType(@Param("leavetypeid") int leavetypeid, @Param("bal") int bal, @Param("staffid") int staffid);
}
