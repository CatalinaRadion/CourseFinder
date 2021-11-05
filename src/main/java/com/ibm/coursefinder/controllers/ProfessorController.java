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

        var professor = service.get(id).get();
        course.setProfessor(professor);
        course = courseService.post(course);

        return course;
    }

    @GetMapping("/api")
    public @ResponseBody
    List<Professor> allProfessors() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Professor professorById(@PathVariable Long id) {
        return service.get(id).get();
    }

    @PostMapping("/new")
    public @ResponseBody
    Professor post(@RequestBody Professor professor) {
        return service.post(professor);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Professor put(@PathVariable Long id, @RequestBody Professor professor) {
        return service.put(id, professor);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    Professor deleteProfessor(@PathVariable Long id) {
        return service.delete(id).get();
    }

}
