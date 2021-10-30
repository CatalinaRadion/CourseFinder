package com.ibm.coursefinder.services;

import com.ibm.coursefinder.DTOs.CourseDTO;
import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.repositories.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseService extends RESTService<Course, CourseDTO, Long> {

    public CourseService(CourseRepository repo) {
        super(repo, CourseDTO::new, Course::new);
    }

    @Override
    public Optional<CourseDTO> put(Long id, CourseDTO newObject) {
        var optionalCourse = repo.findById(id);
        optionalCourse.ifPresent(course -> {
            course.setName(newObject.getName());
            //course.setProfessors(newObject.getProfessorList().stream().map(Professor::new).collect(Collectors.toList()));
        });
        return optionalCourse.map(function);
    }
}
