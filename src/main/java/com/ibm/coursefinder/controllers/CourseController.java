package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseController {
    CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("")
    public String courses(Model model) {

        List<Course> courses = service.getAll();
        model.addAttribute("courses", courses);

        return "courses/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {

        Course course = service.get(id).get();
        model.addAttribute("course", course);

        return "courseView";
    }

    @PostMapping("/new")
    public @ResponseBody
    Course post(Course course) {
        return service.post(course);
    }
}
