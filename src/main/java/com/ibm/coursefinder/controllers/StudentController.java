package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.services.StudentService;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {

        Student student = service.get(id).get();
        model.addAttribute("student", student);

        return "studentView";
    }

    @PostMapping("/new")
    public String post(Student student) {
        service.post(student);
        return "redirect:/students";
    }

    @GetMapping("/new")
    public String postPage(Model model) {
        var student = new Student();
        model.addAttribute("student", student);
        return "students/new";
    }
}
