package com.ibm.coursefinder.userroles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.coursefinder.entities.StudentCourse;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends User {
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<StudentCourse> studentCourses = new HashSet<>();

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
