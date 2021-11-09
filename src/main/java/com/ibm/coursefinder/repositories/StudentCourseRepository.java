package com.ibm.coursefinder.repositories;

import com.ibm.coursefinder.entities.StudentCourse;
import com.ibm.coursefinder.entities.StudentCourseId;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
    @Query("SELECT s FROM Student s join StudentCourse sc on s.id=sc.student.id where sc.course.id=:id")
    Collection<Student> getStudentsByCourseId(Long id);
}