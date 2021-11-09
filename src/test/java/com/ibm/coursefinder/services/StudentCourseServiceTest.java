package com.ibm.coursefinder.services;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.entities.StudentCourse;
import com.ibm.coursefinder.repositories.CourseRepository;
import com.ibm.coursefinder.repositories.StudentCourseRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentCourseServiceTest {

    @Mock
    private StudentCourseRepository repo;
    @Mock
    private StudentRepository studentRepo;
    @Mock
    private CourseRepository courseRepo;

    @Autowired
    @InjectMocks
    private StudentCourseService service;

    private Student student;
    private Course course;
    private StudentCourse studentCourse;

    @BeforeEach
    public void initialize() {
        student = new Student();
        student.setId(1L);
        student.setName("Xulescu");
        student.setDateOfBirth(new Date());
        course = new Course();
        course.setId(1L);
        course.setName("Biologie");
        studentCourse = new StudentCourse(student, course);
    }

    @AfterEach
    public void clear() {
        student = null;
        course = null;
        studentCourse = null;
    }


    @Test
    public void getAllTest() {
        Mockito.when(repo.findAll()).thenReturn(List.of(studentCourse));
        assertEquals(service.getAll(), List.of(studentCourse));
    }

    @Test
    public void getByIdTest() {
        Mockito.when(repo.findById(studentCourse.getId())).thenReturn(Optional.ofNullable(studentCourse));
        assertEquals(service.get(studentCourse.getId()).get(), studentCourse);
    }


    @Test
    public void postTest() {
        Mockito.when(studentRepo.findById(student.getId())).thenReturn(Optional.ofNullable(student));
        Mockito.when(courseRepo.findById(course.getId())).thenReturn(Optional.ofNullable(course));
        Mockito.when(repo.save(studentCourse)).thenReturn(studentCourse);
        assertEquals(service.post(studentCourse.getId()).get(), studentCourse);
    }


}
