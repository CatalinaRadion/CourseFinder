package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.entities.StudentCourse;
import com.ibm.coursefinder.entities.StudentCourseId;
import com.ibm.coursefinder.services.StudentCourseService;
import com.ibm.coursefinder.services.StudentService;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        if (student.validate()) {
            service.post(student);
        }
        return "redirect:/students";
    }

    @PostMapping("/new/api")
    public ResponseEntity<Student> postApi(@Valid @RequestBody Student student) {
        if (!student.validate()) {
            return ResponseEntity.badRequest().body(student);
        }
        return service.post(student)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @GetMapping("/api")
    public @ResponseBody
    List<Student> allStudents() {
        return service.getAll();
    }

    @GetMapping(value = {"/{id}", "/student"})
    public @ResponseBody
    ResponseEntity<Student> studentById(@PathVariable(required = false) Long id
            , @RequestParam(value = "id", required = false) Long studentId) {
        if (id != null) {
            return service.get(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().body(null));
        }
        if (studentId != null) {
            return service.get(studentId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().body(null));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        return service.delete(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));

    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Student> putStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        if (!student.validate()) {
            return ResponseEntity.badRequest().body(student);
        }
        return service.put(id, student).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PostMapping("/enroll")
    public @ResponseBody
    ResponseEntity<StudentCourse> enrollStudent(@RequestBody StudentCourseId id) {
        return studentCourseService.post(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @DeleteMapping("/enroll")
    public @ResponseBody
    ResponseEntity<StudentCourse> unrollStudent(@RequestBody StudentCourseId id) {
        return studentCourseService.delete(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }


}
