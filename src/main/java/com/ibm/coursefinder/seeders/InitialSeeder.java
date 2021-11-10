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

    private StudentCourseId seedOneStudentCourse(int studentId, int courseId) {
        var idStudent = studentService.getAll().get(studentId).getId();
        var idCourse = courseService.getAll().get(courseId).getId();
        var studentCourseId = new StudentCourseId(idStudent, idCourse);
        return studentCourseId;
    }

    private void seedStudentCourses() {
        if (studentCourseService.getAll().size() == 0) {
            studentCourseService.post(seedOneStudentCourse(0,0));
            studentCourseService.post(seedOneStudentCourse(1,0));
            studentCourseService.post(seedOneStudentCourse(2,1));
            studentCourseService.post(seedOneStudentCourse(3,2));
            studentCourseService.post(seedOneStudentCourse(4,1));
            studentCourseService.post(seedOneStudentCourse(5,3));
            studentCourseService.post(seedOneStudentCourse(6,4));
            studentCourseService.post(seedOneStudentCourse(7,5));
            studentCourseService.post(seedOneStudentCourse(8,6));
            studentCourseService.post(seedOneStudentCourse(9,6));
            studentCourseService.post(seedOneStudentCourse(10,7));
            studentCourseService.post(seedOneStudentCourse(11,7));
            studentCourseService.post(seedOneStudentCourse(12,0));
            studentCourseService.post(seedOneStudentCourse(13,0));
            studentCourseService.post(seedOneStudentCourse(14,1));
            studentCourseService.post(seedOneStudentCourse(15,2));
            studentCourseService.post(seedOneStudentCourse(16,1));
            studentCourseService.post(seedOneStudentCourse(17,3));
            studentCourseService.post(seedOneStudentCourse(18,4));
            studentCourseService.post(seedOneStudentCourse(19,5));
            studentCourseService.post(seedOneStudentCourse(20,6));
            studentCourseService.post(seedOneStudentCourse(21,6));
            studentCourseService.post(seedOneStudentCourse(22,7));
            studentCourseService.post(seedOneStudentCourse(23,7));

        }
    }

    private Professor seedOneProfessor(String name, int year, int month, int day) {
        var professor = new Professor();
        var calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        professor.setName(name);
        professor.setDateOfBirth(calendar.getTime());
        return professor;
    }

    private void seedProfessors() {
        if (profService.getAll().size() == 0) {

            profService.post(seedOneProfessor("Carol Davila", 1990, 5,1));
            profService.post(seedOneProfessor("Eugenia Ruse", 1985, 9,13));
            profService.post(seedOneProfessor("Cosmin Valciu", 1993, 2,31));
            profService.post(seedOneProfessor("Alin Mocanu", 1997, 6,24));
            profService.post(seedOneProfessor("Ionela Radu", 1990, 0,26));
            profService.post(seedOneProfessor("Eugenia Ignatencu", 1991, 5,18));
            profService.post(seedOneProfessor("Nina Grosu", 1988, 11,1));
            profService.post(seedOneProfessor("Ion Rusu", 1968, 10,7));
        }
    }

    private Student seedOneStudent(String name, int year, int month, int day) {
        var student = new Student();
        var calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        student.setName(name);
        student.setDateOfBirth(calendar.getTime());
        return student;
    }

    private void seedStudents() {
        if (studentService.getAll().size() == 0) {

            studentService.post(seedOneStudent("Albert Florea", 1997, 2, 24));
            studentService.post(seedOneStudent("Iulian Stan", 1999, 6, 15));
            studentService.post(seedOneStudent("Mirela Gheorghe", 1984, 9, 23));
            studentService.post(seedOneStudent("Catalina Radion", 1998, 8, 27));
            studentService.post(seedOneStudent("Radu Onea", 2000, 4, 3));
            studentService.post(seedOneStudent("Bogdan Petre", 1986, 6, 11));
            studentService.post(seedOneStudent("Andrei Andrei", 2004, 7, 8));
            studentService.post(seedOneStudent("Petre Mihai", 1988, 12, 24));
            studentService.post(seedOneStudent("Miruna Gavrila", 1999, 6, 18));
            studentService.post(seedOneStudent("Iuliana Teodorescu", 1994, 9, 23));
            studentService.post(seedOneStudent("Vlad Petrcu", 1998, 8, 27));
            studentService.post(seedOneStudent("Tudorita Topa", 2007, 0, 3));
            studentService.post(seedOneStudent("Valentina Calin", 1977, 8, 19));
            studentService.post(seedOneStudent("Maria Speranta", 2004, 5, 8));
            studentService.post(seedOneStudent("Lidia Buble", 1997, 2, 24));
            studentService.post(seedOneStudent("Estera Buble", 1999, 6, 15));
            studentService.post(seedOneStudent("Maria Trandafir", 1984, 9, 23));
            studentService.post(seedOneStudent("Catalina ", 1998, 8, 27));
            studentService.post(seedOneStudent("Dumitra Alexe", 2000, 4, 3));
            studentService.post(seedOneStudent("Maria Balta", 1986, 6, 11));
            studentService.post(seedOneStudent("Liliana Picerea", 2004, 7, 8));
            studentService.post(seedOneStudent("Ana Mercan", 1988, 12, 24));
            studentService.post(seedOneStudent("Natalia Botea", 1999, 6, 18));
            studentService.post(seedOneStudent("Gheorhe Chirila", 1994, 9, 23));
            studentService.post(seedOneStudent("Cioleta Lungu", 1998, 8, 27));
            studentService.post(seedOneStudent("TMirela Zigarov", 2007, 0, 3));
            studentService.post(seedOneStudent("Camelia Platica", 1977, 8, 19));
            studentService.post(seedOneStudent("Luca Matei", 2004, 5, 8));
        }
    }

    private Course seedOneCourse(String name, long professorId, String details) {
        var course = new Course();
        course.setName(name);
        course.setProfessor(profService.get(professorId).get());
        var courseDetails = new CourseDetails();
        courseDetails.setCourseDetails(details);
        course.setCourseDetails(courseDetails);
        return course;
    }

    private void seedCourses() {
        if (courseService.getAll().size() == 0) {

            courseService.post(seedOneCourse("Java", 2L, "Java is a high-level, class-based, object-oriented programming language, primarily used for Internet-based applications"));
            courseService.post(seedOneCourse("Python", 1L, "Python is used for web development, AI, machine learning, operating systems, mobile application development, and video games."));
            courseService.post(seedOneCourse("HTML", 8L, "HTML gives structure and style to web pages"));
            courseService.post(seedOneCourse("CSS", 3L, "CSS gives structure and style to web pages"));
            courseService.post(seedOneCourse("JavaScript", 4L, "JavaScript is a text-based programming language used both on the client-side and server-side that allows you to make web pages interactive."));
            courseService.post(seedOneCourse("C++", 2L, "C++ is a powerful general-purpose programming language. It can be used to develop operating systems, browsers, games, and so on."));
            courseService.post(seedOneCourse("C#", 7L, "C# is primarily used on the Windows . NET framework, although it can be applied to an open source platform."));
            courseService.post(seedOneCourse("PHP", 1L, "PHP (Hypertext Preprocessor) is known as a general-purpose scripting language that can be used to develop dynamic and interactive websites."));
            courseService.post(seedOneCourse("Ruby", 6L, "Ruby is most used for building web applications. However, it is a general-purpose language similar to Python, so it has many other applications like data analysis, prototyping, and proof of concepts. "));
            courseService.post(seedOneCourse("SQL", 5L, "SQL (Structured Query Language) is a standardized programming language that's used to manage relational databases and perform various operations on the data in them."));
            courseService.post(seedOneCourse("SprigBoot", 2L, "Spring Boot lets you create standalone applications that run on their own, without relying on an external web server, by embedding a web server such as Tomcat or Netty into your app during the initialization process."));
            courseService.post(seedOneCourse("Thymeleaf", 8L, "Thymeleaf is a Java-based library used to create a web application. It provides a good support for serving a XHTML/HTML5 in web applications."));
        }
    }
}
