package com.ibm.coursefinder.entities;

import com.ibm.coursefinder.DTOs.CourseDTO;
import com.ibm.coursefinder.userroles.Professor;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> studentCourses;

    public Course(CourseDTO courseDTO) {
        setName(courseDTO.getName());
    }

    public Course() {
    }


    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }


    public com.ibm.coursefinder.userroles.Professor getProfessor() {
        return professor;
    }

    public void setProfessor(com.ibm.coursefinder.userroles.Professor professor) {
        this.professor = professor;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
