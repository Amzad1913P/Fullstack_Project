package back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import back.entity.Course;
import back.repository.CourseRepository;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        return courseRepo.save(course);
    }

    @GetMapping("/all")
    public Object getAllCourses() {
        return courseRepo.findAll();
    }
}