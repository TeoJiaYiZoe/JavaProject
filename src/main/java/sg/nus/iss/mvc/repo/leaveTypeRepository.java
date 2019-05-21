package sg.nus.iss.mvc.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mvc.model.leaveType;

@Repository
public interface leaveTypeRepository extends JpaRepository<leaveType,String>{

}
