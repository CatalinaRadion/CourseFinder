package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.StudentRepository;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
