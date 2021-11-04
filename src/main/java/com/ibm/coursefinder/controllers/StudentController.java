package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.services.StudentService;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {
    StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
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
    public Student postApi(Student student) {
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
    Student deleteStudent(@PathVariable Long id, Student student) {
        return service.put(id, student).get();
    }


}
