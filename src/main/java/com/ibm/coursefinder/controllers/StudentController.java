package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.StudentCourse;
import com.ibm.coursefinder.entities.StudentCourseId;
import com.ibm.coursefinder.services.StudentCourseService;
import com.ibm.coursefinder.services.StudentService;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("students")
public class StudentController {
    StudentService service;
    StudentCourseService studentCourseService;

    public StudentController(StudentService service, StudentCourseService studentCourseService) {
        this.service = service;
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("")
    public String students(Model model) {

        List<Student> students = service.getAll();
        model.addAttribute("students", students);

        return "students/index";
    }

    @GetMapping("/new")
    public String postPage(Model model) {
        var student = new Student();
        model.addAttribute("student", student);
        return "students/new";
    }

    @PostMapping("/new")
    public String post(Student student) {
        service.post(student);
        return "redirect:/students";
    }

    @PostMapping("/new/api")
    public @ResponseBody
    Student postApi(@RequestBody Student student) {
        return service.post(student);

    }

    @GetMapping("/api")
    public @ResponseBody
    List<Student> allStudents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Student studentById(@PathVariable Long id) {
        return service.get(id).get();
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    Student deleteStudent(@PathVariable Long id) {
        return service.delete(id).get();
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Student putStudent(@PathVariable Long id, @RequestBody Student student) {
        return service.put(id, student);
    }
    
    @PostMapping("/enroll")
    public @ResponseBody
    StudentCourse enrollStudent(@RequestBody StudentCourseId id) {
        return studentCourseService.post(id);
    }

    @DeleteMapping("/enroll")
    public @ResponseBody
    Optional<StudentCourse> unenrollStudent(@RequestBody StudentCourseId id) {
        return studentCourseService.delete(id);
    }


}
