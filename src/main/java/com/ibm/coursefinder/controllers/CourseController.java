package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import com.ibm.coursefinder.services.ProfessorService;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseController {
    CourseService service;
    ProfessorService profService;


    public CourseController(CourseService service, ProfessorService profService) {
        this.service = service;
        this.profService = profService;
        var prof = new Professor();
        prof.setName("Carol");
        prof.setDateOfBirth(new Date());
        profService.post(prof);
        var course = new Course();
        course.setName("Matematica");
        course.setProfessor(profService.get(1L).get());
        service.post(course);
    }

    @GetMapping("")
    public @ResponseBody
    List<Course> getAll() {
        return service.getAll();
    }

    @PostMapping("/new")
    public @ResponseBody
    Course post(Course course) {
        return service.post(course);
    }


    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("course", service.get(id).get());
        return "courseView";
    }

}
