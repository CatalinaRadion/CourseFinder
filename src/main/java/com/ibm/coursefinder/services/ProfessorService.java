package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.ProfessorRepository;
import com.ibm.coursefinder.userroles.Professor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService extends RESTService<Professor, Long> {

    public ProfessorService(ProfessorRepository repo) {
        super(repo);
    }

    @Override
    public Optional<Professor> put(Long id, Professor newObject) {
        try {
            var optionalProfessor = repo.findById(id);
            optionalProfessor.ifPresent(professor -> {
                professor.setName(newObject.getName());
                professor.setDateOfBirth(newObject.getDateOfBirth());
            });
            return Optional.of(repo.save(optionalProfessor.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
