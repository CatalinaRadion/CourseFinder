package com.ibm.coursefinder.services;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.entities.CourseDetails;
import com.ibm.coursefinder.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends RESTService<Course, Long> {

    public CourseService(CourseRepository repo) {
        super(repo);
    }

    @Override
    public Course put(Long id, Course newObject) {
        var optionalCourse = repo.findById(id);
        optionalCourse.ifPresent(course -> {
            course.setName(newObject.getName());
        });
        return optionalCourse.get();
    }
}
