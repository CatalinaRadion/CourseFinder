package com.ibm.coursefinder.services;


import com.ibm.coursefinder.entities.StudentCourse;
import com.ibm.coursefinder.entities.StudentCourseId;
import com.ibm.coursefinder.repositories.CourseRepository;
import com.ibm.coursefinder.repositories.StudentCourseRepository;
import com.ibm.coursefinder.repositories.StudentRepository;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {

    private StudentCourseRepository repo;
    private StudentRepository studentRepo;
    private CourseRepository courseRepo;


    public StudentCourseService(StudentCourseRepository repo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public List<StudentCourse> getAll() {
        return repo.findAll();
    }

    public Optional<StudentCourse> get(StudentCourseId id) {
        return repo.findById(id);
    }

    public Optional<StudentCourse> delete(StudentCourseId id) {
        var opt = repo.findById(id);
        opt.ifPresent(($) -> repo.deleteById(id));
        return opt;
    }

    public StudentCourse post(StudentCourseId id) {
        var course = courseRepo.findById(id.getCourseId()).get();
        var student = studentRepo.findById(id.getStudentId()).get();
        var stcourse = new StudentCourse(student, course);
        return repo.save(stcourse);
    }

    public Collection<Student> getAllStudentsAssignedToCourseId(Long courseId) {
        return repo.getStudentsByCourseId(courseId);
    }


}
