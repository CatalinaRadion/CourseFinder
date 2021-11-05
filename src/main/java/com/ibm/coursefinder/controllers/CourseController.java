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

    @GetMapping("/api")
    public @ResponseBody
    List<Course> allCourses() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Course courseById(@PathVariable Long id) {
        return service.get(id).get();
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    Course deleteCourse(@PathVariable Long id) {
        return service.delete(id).get();
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Course putCourse(@PathVariable Long id, @RequestBody Course course) {
        return service.put(id, course);
    }

}
