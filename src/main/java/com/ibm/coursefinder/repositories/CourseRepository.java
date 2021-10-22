package com.ibm.coursefinder.repositories;

import com.ibm.coursefinder.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}