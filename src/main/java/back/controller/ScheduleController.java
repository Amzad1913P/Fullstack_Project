package back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import back.entity.Course;
import back.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Check Time Conflict
    @PostMapping("/check")
    public String checkConflict(
            @RequestParam Long studentId,
            @RequestBody Course course) {

        boolean conflict =
                scheduleService.checkConflict(studentId, course);

        if(conflict)
            return "Schedule Conflict Detected";

        return "No Conflict";
    }
}
