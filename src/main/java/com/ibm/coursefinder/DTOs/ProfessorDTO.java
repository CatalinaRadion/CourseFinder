package com.ibm.coursefinder.DTOs;

import com.ibm.coursefinder.userroles.Professor;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorDTO {
    private String name;
    private Long id;
    private List<CourseDTO> courseList;

    public ProfessorDTO(Professor professorDAO) {
        name = professorDAO.getName();
        id = professorDAO.getId();
        courseList = professorDAO.getCourses()
                .stream().map(CourseDTO::new)
                .collect(Collectors.toList());
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

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
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
