package com.ibm.coursefinder.controllers;

import com.ibm.coursefinder.DTOs.CourseDTO;
import com.ibm.coursefinder.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {
    CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("")
    public @ResponseBody
    List<CourseDTO> getAll() {
        return service.getAll();
    }

    @PostMapping("/new")
    public @ResponseBody
    CourseDTO post(CourseDTO course) {
        return service.post(course);
    }
}
