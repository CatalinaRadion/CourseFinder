package com.ibm.coursefinder.services;

import com.ibm.coursefinder.repositories.StudentCourseRepository;
import com.ibm.coursefinder.repositories.StudentRepository;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends RESTService<Student, Long> {


    private StudentCourseRepository studentCourseRepository;

    public StudentService(StudentRepository repo, StudentCourseRepository studentCourseRepository) {
        super(repo);
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public Student put(Long id, Student newObject) {
        var optionalStudent = repo.findById(id);
        optionalStudent.ifPresent(course -> {
            course.setName(newObject.getName());
            course.setDateOfBirth(newObject.getDateOfBirth());
        });
        return repo.save(optionalStudent.get());

    }

}
