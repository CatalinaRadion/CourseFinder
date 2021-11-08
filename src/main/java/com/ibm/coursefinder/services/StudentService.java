package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.StudentCourseRepository;
import com.ibm.coursefinder.repositories.StudentRepository;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService extends RESTService<Student, Long> {


    private StudentCourseRepository studentCourseRepository;

    public StudentService(StudentRepository repo, StudentCourseRepository studentCourseRepository) {
        super(repo);
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public Optional<Student> put(Long id, Student newObject) {
        try {
            var optionalStudent = repo.findById(id);
            optionalStudent.ifPresent(course -> {
                course.setName(newObject.getName());
                course.setDateOfBirth(newObject.getDateOfBirth());
            });
            return Optional.of(repo.save(optionalStudent.get()));
        } catch (Exception e) {
            return Optional.empty();
        }

    }

}
