package com.ibm.coursefinder.controllers;


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

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("")
    public String students(Model model) {

        List<Professor> professors = service.getAll();
        model.addAttribute("professors", professors);

        return "professors/index";
    }

    @PostMapping("/new")
    public @ResponseBody
    Professor post(@RequestBody Professor professor) {
        return service.post(professor);
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("professor", service.get(id).get());
        return "professorView";
    }


}
