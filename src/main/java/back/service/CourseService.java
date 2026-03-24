package back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import back.entity.Course;
import back.repository.CourseRepository;
import back.repository.EnrollmentRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    // ==========================
    // ADD COURSE
    // ==========================
    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    // ==========================
    // GET ALL COURSES
    // ==========================
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // ==========================
    // DELETE COURSE + ENROLLMENTS
    // ==========================
    public String deleteCourse(Long courseId) {

        Course course = courseRepo.findById(courseId).orElse(null);

        if (course == null) {
            return "Course not found";
        }

        // 🔥 Delete all enrollments related to this course
        enrollmentRepo.deleteByCourse(course);

        // 🔥 Delete the course
        courseRepo.deleteById(courseId);

        return "Course and related enrollments deleted successfully";
    }
}