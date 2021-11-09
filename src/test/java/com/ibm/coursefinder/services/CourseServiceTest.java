package com.ibm.coursefinder.services;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.repositories.CourseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository repo;

    @Autowired
    @InjectMocks
    private CourseService service;

    private Course course;

    @BeforeEach
    public void initialize() {
        course = new Course();
        course.setId(1L);
        course.setName("Biologie");
    }

    @AfterEach
    public void clear() {
        course = null;
    }

    @Test
    public void putTest() {
        Course replaceCourse = new Course();
        replaceCourse.setId(2L);
        replaceCourse.setName("Anatomie");

        Mockito.when(repo.findById(course.getId())).thenReturn(Optional.ofNullable(course));
        course.setName(replaceCourse.getName());
        Mockito.when(repo.save(course)).thenReturn(course);
        assertEquals(service.put(course.getId(), replaceCourse).get(), course);

    }
}
