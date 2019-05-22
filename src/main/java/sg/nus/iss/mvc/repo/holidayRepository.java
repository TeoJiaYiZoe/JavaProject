package sg.nus.iss.mvc.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mvc.model.holiday;

@Repository
public interface holidayRepository extends JpaRepository<holiday,String>{
	@Query("SELECT h.date FROM holiday h")
	List<LocalDate> findAllDate();
}
