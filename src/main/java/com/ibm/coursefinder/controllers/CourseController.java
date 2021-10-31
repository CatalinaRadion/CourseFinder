package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController
//@RequestMapping("courses")
@Controller
public class CourseController {
    CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("courses")
    public String courses(Model model) {

        String intro = "Courses available:";
        model.addAttribute("intro", intro);

        List<Course> courses = getAllCourses();
        model.addAttribute("course", courses);

        return "courses";
    }

    //@GetMapping("courses")
    public @ResponseBody
    List<Course> getAllCourses() {
        return service.getAll();
    }

    @PostMapping("/new")
    public @ResponseBody
    Course post(Course course) {
        return service.post(course);
    }
}
