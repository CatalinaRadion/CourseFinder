package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import com.ibm.coursefinder.services.ProfessorService;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("professors")
public class ProfessorController {
    ProfessorService service;
    CourseService courseService;

    public ProfessorController(ProfessorService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @GetMapping("")
    public String students(Model model) {

        List<Professor> professors = service.getAll();
        model.addAttribute("professors", professors);

        return "professors/index";
    }

    @PostMapping("/addCourse/{id}")
    public ResponseEntity<Course> post(@PathVariable Long id, @RequestBody Course course) {

        var professor = service.get(id).get();
        course.setProfessor(professor);
        course = courseService.post(course).get();

        if (!course.validate()) {
            return ResponseEntity.badRequest().body(course);
        }

        return ResponseEntity.ok(courseService.post(course).get());
    }

    @GetMapping(value = "/api",
             produces = { MediaType.APPLICATION_JSON_VALUE,
                          MediaType.APPLICATION_XML_VALUE,
                          MediaType.TEXT_PLAIN_VALUE })
    //@Produces("text/plain")
    public @ResponseBody
    List<Professor> allProfessors() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Professor professorById(@PathVariable Long id) {
        return service.get(id).get();
    }

    @PostMapping(value = "/new",
                 consumes = { MediaType.APPLICATION_JSON_VALUE,
                              MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Professor> post(@Valid @RequestBody Professor professor) {
        if (!professor.validate()) {
            return ResponseEntity.badRequest().body(professor);
        }
        return ResponseEntity.ok(service.post(professor).get());
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Professor put(@PathVariable Long id, @RequestBody Professor professor) {
        return service.put(id, professor).get();
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    Professor deleteProfessor(@PathVariable Long id) {
        return service.delete(id).get();
    }

}
