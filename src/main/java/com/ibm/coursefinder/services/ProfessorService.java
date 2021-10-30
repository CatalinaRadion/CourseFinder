package com.ibm.coursefinder.services;

import com.ibm.coursefinder.DTOs.ProfessorDTO;
import com.ibm.coursefinder.repositories.ProfessorRepository;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfessorService extends RESTService<Professor, ProfessorDTO, Long> {

    public ProfessorService(ProfessorRepository repo) {
        super(repo, ProfessorDTO::new, Professor::new);
    }

    @Override
    public Optional<ProfessorDTO> put(Long id, ProfessorDTO newObject) {
        var optionalProfessor = repo.findById(id);
        optionalProfessor.ifPresent(course -> {
            course.setName(newObject.getName());
            //course.setProfessors(newObject.getProfessorList().stream().map(Professor::new).collect(Collectors.toList()));
        });
        return optionalProfessor.map(function);
    }
}
