package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.ProfessorRepository;
import com.ibm.coursefinder.userroles.Professor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @Mock
    private ProfessorRepository repo;

    @Autowired
    @InjectMocks
    private ProfessorService service;

    private Professor professor;

    @BeforeEach
    public void initialize() {
        professor = new Professor();
        professor.setId(1L);
        professor.setName("Petrica");
        professor.setDateOfBirth(new Date());
    }

    @AfterEach
    public void clear() {
        professor = null;
    }

    @Test
    public void postTest() {
        Mockito.when(repo.save(professor)).thenReturn(professor);
        assertEquals(service.post(professor).get(),professor);
    }

    @Test
    public void getByIdTest() {
        Mockito.when(repo.findById(professor.getId())).thenReturn(Optional.ofNullable(professor));
        assertEquals(service.get(professor.getId()).get(), professor);
    }

    @Test
    public void getAllTest() {
        Professor professor2 = new Professor();
        professor2.setId(2L);
        professor2.setName("Profirescu");
        professor2.setDateOfBirth(new Date());

        Mockito.when(repo.findAll()).thenReturn(List.of(professor, professor2));
        assertEquals(service.getAll(), List.of(professor, professor2));
    }

    @Test
    public void deleteTest() {
        Mockito.when(repo.findById(professor.getId())).thenReturn(Optional.ofNullable(professor));
        assertThat(service.delete(professor.getId()).get()).isEqualTo(professor);
    }

    @Test
    public void putTest() {
        Professor replaceProfessor = new Professor();
        replaceProfessor.setId(2L);
        replaceProfessor.setName("Marghescu");
        replaceProfessor.setDateOfBirth(new Date());

        Mockito.when(repo.findById(professor.getId())).thenReturn(Optional.ofNullable(professor));
        professor.setName(replaceProfessor.getName());
        professor.setDateOfBirth(replaceProfessor.getDateOfBirth());
        Mockito.when(repo.save(professor)).thenReturn(professor);
        assertEquals(service.put(professor.getId(),replaceProfessor).get(), professor);

    }

}
