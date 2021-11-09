package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.ProfessorRepository;
import com.ibm.coursefinder.userroles.Professor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @Mock
    private ProfessorRepository repo;

    @Autowired
    @InjectMocks
    private ProfessorService service;

    @Test
    public void postTest() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Petrica");
        professor.setDateOfBirth(new Date());

        Mockito.when(repo.save(professor)).thenReturn(professor);
        assertEquals(service.post(professor).get(),professor);

    }




}
