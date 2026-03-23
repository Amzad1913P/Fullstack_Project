package back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import back.service.EnrollmentService;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    @PostMapping("/add")
    public String enroll(@RequestParam Long studentId,
                         @RequestParam Long courseId) {

        return service.addCourse(studentId, courseId);
    }

    @GetMapping("/list")
    public Object list(@RequestParam Long studentId) {
        return service.listCourses(studentId);
    }
}