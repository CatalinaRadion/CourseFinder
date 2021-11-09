package com.ibm.coursefinder.seeders;


import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.entities.CourseDetails;
import com.ibm.coursefinder.entities.StudentCourseId;
import com.ibm.coursefinder.services.CourseService;
import com.ibm.coursefinder.services.ProfessorService;
import com.ibm.coursefinder.services.StudentCourseService;
import com.ibm.coursefinder.services.StudentService;
import com.ibm.coursefinder.userroles.Professor;
import com.ibm.coursefinder.userroles.Student;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class InitialSeeder {
    private CourseService courseService;
    private ProfessorService profService;
    private StudentService studentService;
    private StudentCourseService studentCourseService;

    public InitialSeeder(CourseService courseService, ProfessorService profService, StudentService studentService, StudentCourseService studentCourseService) {
        this.courseService = courseService;
        this.profService = profService;
        this.studentService = studentService;
        this.studentCourseService = studentCourseService;
    }

    public void seed() {
        seedProfessors();
        seedCourses();
        seedStudents();
        seedStudentCourses();
    }

    private void seedStudentCourses() {
        var idStudent = studentService.getAll().get(0).getId();
        var idCourse = courseService.getAll().get(0).getId();
        studentCourseService.post(new StudentCourseId(idStudent, idCourse));
    }


    private void seedProfessors() {
        var prof = new Professor();
        var calendar = Calendar.getInstance();
        calendar.set(1990, Calendar.AUGUST, 1);
        prof.setDateOfBirth(calendar.getTime());
        prof.setName("Carol");
        prof = profService.post(prof).get();

    }

    private void seedStudents() {
        var student = new Student();
        var calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.OCTOBER, 31);
        student.setDateOfBirth(calendar.getTime());
        student.setName("Albert");
        student = studentService.post(student).get();

    }

    private void seedCourses() {
        var course = new Course();
        course.setName("Matematica");
        course.setProfessor(profService.get(1L).get());
        var detail = new CourseDetails();
        detail.setCourseDetails("Algebra 1");
        course.setCourseDetails(detail);
        courseService.post(course);

        var course2 = new Course();
        course2.setName("Fizica");
        course2.setProfessor(profService.get(1L).get());
        var detail2 = new CourseDetails();
        detail2.setCourseDetails("Mecanica cuantica");
        course2.setCourseDetails(detail2);
        courseService.post(course2);

    }


}
