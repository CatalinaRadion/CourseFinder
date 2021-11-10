package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import com.ibm.coursefinder.services.ProfessorService;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("professors")
public class ProfessorController {
    ProfessorService service;
    CourseService courseService;

    public ProfessorController(ProfessorService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @GetMapping("")
    public String professors(Model model) {

        List<Professor> professors = service.getAll();
        model.addAttribute("professors", professors);

        return "professors/index";
    }

    @PostMapping("/addCourse/{id}")
    public ResponseEntity<Course> post(@PathVariable Long id, @RequestBody Course course) {

        if (!course.validate()) {
            return ResponseEntity.badRequest().body(course);
        }
        var professor = service.get(id).get();
        course.setProfessor(professor);
        course = courseService.post(course).get();

        return courseService.post(course).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @GetMapping(value = "/api",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_PLAIN_VALUE})
    //@Produces("text/plain")
    public @ResponseBody
    List<Professor> allProfessors() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Professor> professorById(@PathVariable Long id) {
        return service.get(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PostMapping(value = "/new",
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Professor> post(@Valid @RequestBody Professor professor) {
        if (!professor.validate()) {
            return ResponseEntity.badRequest().body(professor);
        }
        return service.post(professor).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Professor> put(@PathVariable Long id, @RequestBody Professor professor) {
        if (!professor.validate()) {
            return ResponseEntity.badRequest().body(professor);
        }
        return service.put(id, professor).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Professor> deleteProfessor(@PathVariable Long id) {
        return service.delete(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

}
