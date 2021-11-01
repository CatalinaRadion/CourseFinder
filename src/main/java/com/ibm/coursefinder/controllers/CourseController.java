package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.entities.CourseDetails;
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
        prof.setDateOfBirth(new Date());
        prof.setName("Carol");
        prof = profService.post(prof);

        var course = new Course();
        course.setName("Matematica");
        course.setProfessor(prof);
        var detail = new CourseDetails();
        detail.setCourseDetails("Algebra 1");
        course.setCourseDetails(detail);
        service.post(course);
    }

    @GetMapping("")
    public String courses(Model model) {

        String intro = "Courses available:";
        model.addAttribute("intro", intro);

        List<Course> courses = service.getAll();
        model.addAttribute("course", courses);

        return "courses";
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {

        Course course = service.get(id).get();
        model.addAttribute("course", course);

        return "courses";
    }

    @PostMapping("/new")
    public @ResponseBody
    Course post(Course course) {
        return service.post(course);
    }
}
