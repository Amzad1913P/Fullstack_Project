package back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import back.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}