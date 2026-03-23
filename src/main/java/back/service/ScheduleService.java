package back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import back.entity.Course;
import back.entity.Enrollment;
import back.entity.Student;
import back.repository.EnrollmentRepository;
import back.repository.StudentRepository;

@Service
public class ScheduleService {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private StudentRepository studentRepo;

    public boolean checkConflict(Long studentId, Course newCourse) {

        Student student = studentRepo.findById(studentId).orElse(null);

        if (student == null) {
            return false;
        }

        List<Enrollment> enrollments =
                enrollmentRepo.findByStudent(student);

        for (Enrollment e : enrollments) {

            // Check day overlap
            String existingDays = e.getDays();
            String newDays = newCourse.getDays();

            boolean dayConflict = false;

            for (int i = 0; i < 6; i++) {
                if (existingDays.charAt(i) == '1'
                        && newDays.charAt(i) == '1') {
                    dayConflict = true;
                    break;
                }
            }

            if (dayConflict) {

                // Check time overlap
                boolean timeConflict =
                        newCourse.getStartTime().isBefore(e.getEndTime())
                                &&
                                newCourse.getEndTime().isAfter(e.getStartTime());

                if (timeConflict) {
                    return true;
                }
            }
        }

        return false;
    }
}