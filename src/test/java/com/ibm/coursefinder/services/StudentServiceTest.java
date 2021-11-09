package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.StudentRepository;
import com.ibm.coursefinder.userroles.Student;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository repo;

    @Autowired
    @InjectMocks
    private StudentService service;

    private Student student;

    @BeforeEach
    public void initialize() {
        student = new Student();
        student.setId(1L);
        student.setName("Xulescu");
        student.setDateOfBirth(new Date());
    }

    @AfterEach
    public void clear() {
        student = null;
    }

    @Test
    public void putTest() {
        Student replaceStudent = new Student();
        replaceStudent.setId(2L);
        replaceStudent.setName("Andrei");
        replaceStudent.setDateOfBirth(new Date());

        Mockito.when(repo.findById(student.getId())).thenReturn(Optional.ofNullable(student));
        student.setName(replaceStudent.getName());
        student.setDateOfBirth(replaceStudent.getDateOfBirth());
        Mockito.when(repo.save(student)).thenReturn(student);
        assertEquals(service.put(student.getId(),replaceStudent).get(), student);
    }
}
