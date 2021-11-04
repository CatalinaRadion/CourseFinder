package com.ibm.coursefinder.userroles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.coursefinder.entities.Course;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor extends User implements Serializable {
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
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

    @Override
    public String toString() {
        return "Professor{" +
                "} " + super.toString();
    }
}
