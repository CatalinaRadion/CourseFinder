package com.ibm.coursefinder.DTOs;

import com.ibm.coursefinder.userroles.Professor;

import java.util.Set;
import java.util.stream.Collectors;

public class ProfessorDTO {
    private String name;
    private Long id;
    private Set<CourseDTO> courseList;

    public ProfessorDTO(Professor professor) {
        name = professor.getName();
        id = professor.getId();
    }

    public ProfessorDTO() {
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

    public Set<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "ProfessorDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", courseList=" + courseList.stream().map(CourseDTO::toDeepString)
                .collect(Collectors.toList()) +
                '}';
    }

    public String toDeepString() {
        return "ProfessorDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
