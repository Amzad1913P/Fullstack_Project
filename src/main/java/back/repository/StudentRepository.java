package back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import back.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by email (used for login)
    Optional<Student> findByEmail(String email);

    // Find student by roll number (optional, useful later)
    Optional<Student> findByRollno(String rollno);

    // Check if email already exists (for registration validation)
    boolean existsByEmail(String email);
}