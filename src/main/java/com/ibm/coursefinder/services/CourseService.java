package com.ibm.coursefinder.services;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService extends RESTService<Course, Long> {

    public CourseService(CourseRepository repo) {
        super(repo);
    }

    @Override
    public Optional<Course> put(Long id, Course newObject) {
        try {
            var optionalCourse = repo.findById(id);
            optionalCourse.ifPresent(course -> {
                course.setName(newObject.getName());
            });
            return Optional.of(repo.save(optionalCourse.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
