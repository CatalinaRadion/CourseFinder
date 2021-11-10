package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.services.CourseService;
import com.ibm.coursefinder.services.StudentCourseService;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseController {
    CourseService service;
    StudentCourseService studentCourseService;

    public CourseController(CourseService service, StudentCourseService studentCourseService) {
        this.service = service;
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("")
    public String courses(Model model) {

        List<Course> courses = service.getAll();
        model.addAttribute("courses", courses);

        return "courses/index";
    }

    @GetMapping("/{id}")
    public String courseById(@PathVariable Long id, Model model) {
        try {
            Course course = service.get(id).get();
            Collection<Student> students = studentCourseService.getAllStudentsAssignedToCourseId(id);
            model.addAttribute("course", course);
            model.addAttribute("students", students);
        } catch (Exception e) {
            return "error";
        }

        return "courses/courseView";
    }

    @GetMapping("/api")
    public @ResponseBody
    List<Course> allCourses() {
        return service.getAll();
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Course> courseById(@PathVariable Long id) {
        return service.get(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        return service.delete(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Course> putCourse(@PathVariable Long id, @RequestBody Course course) {
        if (!course.validate()) {
            return ResponseEntity.badRequest().body(course);
        }
        return service.put(id, course).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @GetMapping("/{id}/students")
    public @ResponseBody
    Collection<Student> studentsByCourseId(@PathVariable Long id) {
        return studentCourseService.getAllStudentsAssignedToCourseId(id);
    }

}
