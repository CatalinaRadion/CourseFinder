package com.ibm.coursefinder.DTOs;

import com.ibm.coursefinder.entities.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO {
    private String name;
    private Long id;
    private List<ProfessorDTO> professorList;

    public CourseDTO(Course courseDAO) {
        name = courseDAO.getName();
        id = courseDAO.getId();
        professorList = courseDAO.getProfessors()
                .stream().map(ProfessorDTO::new)
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

    public List<ProfessorDTO> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<ProfessorDTO> professorList) {
        this.professorList = professorList;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", professorList=" + professorList.stream().map(ProfessorDTO::toDeepString)
                .collect(Collectors.toList()) +
                '}';
    }

    public String toDeepString() {
        return "CourseDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
