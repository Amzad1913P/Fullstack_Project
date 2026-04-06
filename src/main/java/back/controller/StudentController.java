package back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import back.entity.Student;
import back.repository.StudentRepository;
import back.repository.EnrollmentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteStudent(@PathVariable Long id) {
        return studentRepo.findById(id).map(student -> {
            enrollmentRepo.deleteByStudent(student);
            studentRepo.delete(student);
            return "Student deleted successfully";
        }).orElse("Student not found");
    }
}