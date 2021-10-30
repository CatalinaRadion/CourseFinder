package com.ibm.coursefinder.controllers;


import com.ibm.coursefinder.DTOs.ProfessorDTO;
import com.ibm.coursefinder.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professors")
public class ProfessorController {
    ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("")
    public @ResponseBody
    List<ProfessorDTO> getAll() {
        return service.getAll();
    }

    @PostMapping("/new")
    public @ResponseBody
    ProfessorDTO post(@RequestBody ProfessorDTO professorDTO) {
        return service.post(professorDTO);
    }


}
