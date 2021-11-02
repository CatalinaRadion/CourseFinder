package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.StudentRepository;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentService extends RESTService<Student, Long> {

    public StudentService(StudentRepository repo) {
        super(repo);
    }

    @Override
    public Optional<Student> put(Long id, Student newObject) {
        var optionalStudent = repo.findById(id);
        optionalStudent.ifPresent(course -> {
            course.setName(newObject.getName());
        });
        return optionalStudent;
    }
}
