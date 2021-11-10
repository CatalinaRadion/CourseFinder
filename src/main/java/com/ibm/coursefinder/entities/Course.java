package com.ibm.coursefinder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.coursefinder.userroles.Professor;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE},
            optional = false, orphanRemoval = true)
    private CourseDetails courseDetails;

    @ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    @JsonIgnore
    private Professor professor;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<StudentCourse> studentCourses = new HashSet<>();

    public Course() {
    }


    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public CourseDetails getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(CourseDetails courseDetails) {
        if (courseDetails == null) {
            if (this.courseDetails != null) {
                this.courseDetails.setCourse(null);
            }
        } else {
            courseDetails.setCourse(this);
        }
        this.courseDetails = courseDetails;
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

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeObject("12");
    }

    public boolean validate() {
        return (name != null
                && !name.isEmpty()
                && courseDetails != null
                && courseDetails.getCourseDetails() != null
                && !courseDetails.getCourseDetails().isEmpty());
    }
}
