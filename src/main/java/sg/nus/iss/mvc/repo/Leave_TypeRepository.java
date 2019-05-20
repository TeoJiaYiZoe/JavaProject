package sg.nus.iss.mvc.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mvc.model.Leave_Type;

@Repository
public interface Leave_TypeRepository extends JpaRepository<Leave_Type,String>{

}
