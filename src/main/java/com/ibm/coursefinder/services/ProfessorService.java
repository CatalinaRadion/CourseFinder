package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.ProfessorRepository;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfessorService extends RESTService<Professor, Long> {

    public ProfessorService(ProfessorRepository repo) {
        super(repo);
    }

    @Override
    public Optional<Professor> put(Long id, Professor newObject) {
        var optionalProfessor = repo.findById(id);
        optionalProfessor.ifPresent(course -> {
            course.setName(newObject.getName());
        });
        return optionalProfessor;
    }
}
