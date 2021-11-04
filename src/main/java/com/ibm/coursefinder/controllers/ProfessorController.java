package com.ibm.coursefinder.controllers;


import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import com.ibm.coursefinder.services.ProfessorService;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("professors")
public class ProfessorController {
    ProfessorService service;
    CourseService courseService;

    public ProfessorController(ProfessorService service,CourseService courseService) {
        this.service = service;
        this.courseService=courseService;
    }

    @GetMapping("")
    public String students(Model model) {

        List<Professor> professors = service.getAll();
        model.addAttribute("professors", professors);

        return "professors/index";
    }

    @PostMapping("/addCourse/{id}")
    public @ResponseBody
    Course post(@PathVariable Long id,@RequestBody Course course) {

        var prof=service.get(id).get();
        course.setProfessor(prof);
        course=courseService.post(course);

        return course;
    }


    @PostMapping("/new")
    public @ResponseBody
    Professor post(@RequestBody Professor professor) {
        return service.post(professor);
    }

    @GetMapping("/{id}")
    public @ResponseBody Professor get(@PathVariable Long id) {
        return service.get(id).get();
    }


}
