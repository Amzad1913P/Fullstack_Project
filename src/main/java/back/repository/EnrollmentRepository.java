package back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import back.entity.Enrollment;
import back.entity.Student;
import back.entity.Course;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(Student student);

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
}