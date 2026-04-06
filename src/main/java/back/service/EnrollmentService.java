package back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import back.entity.Course;
import back.entity.Enrollment;
import back.entity.Student;
import back.repository.CourseRepository;
import back.repository.EnrollmentRepository;
import back.repository.StudentRepository;

@Service
@Transactional
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ScheduleService scheduleService;

    public String addCourse(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId).orElse(null);
        Course course = courseRepo.findById(courseId).orElse(null);

        if (student == null || course == null) {
            return "Student or Course not found";
        }

        // prevent duplicate enrollment
        Optional<Enrollment> existing =
                enrollmentRepo.findByStudentAndCourse(student, course);

        if (existing.isPresent()) {
            return "Already Enrolled";
        }

        // check schedule conflict
        boolean conflict = scheduleService.checkConflict(studentId, course);

        if (conflict) {
            return "Schedule Conflict! Enrollment Failed";
        }

        // create enrollment snapshot
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        enrollment.setStartTime(course.getStartTime());
        enrollment.setEndTime(course.getEndTime());
        enrollment.setDays(course.getDays());

        enrollmentRepo.save(enrollment);

        return "Enrollment Successful";
    }

    public List<Enrollment> listCourses(Long studentId) {

        Student student = studentRepo.findById(studentId).orElse(null);

        if (student == null) {
            return new ArrayList<>();
        }

        return enrollmentRepo.findByStudent(student);
    }
    public String deleteCourse(Long courseId) {

        Course course = courseRepo.findById(courseId).orElse(null);

        if (course == null) {
            return "Course not found";
        }

        // 🔥 STEP 1: delete all enrollments related to this course
        enrollmentRepo.deleteByCourse(course);

        // 🔥 STEP 2: delete course
        courseRepo.deleteById(courseId);

        return "Course and related enrollments deleted successfully";
    }
}