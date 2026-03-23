package back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import back.entity.Course;

@Repository
public interface ScheduleRepository extends JpaRepository<Course, Long> {
}
