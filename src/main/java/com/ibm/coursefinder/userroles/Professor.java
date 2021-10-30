package com.ibm.coursefinder.userroles;


import com.ibm.coursefinder.DTOs.ProfessorDTO;
import com.ibm.coursefinder.entities.Course;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor extends User {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "professor_id")
    private Set<Course> courses = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Professor() {
    }

    public Professor(ProfessorDTO professorDTO) {
        setName(professorDTO.getName());
    }


}
