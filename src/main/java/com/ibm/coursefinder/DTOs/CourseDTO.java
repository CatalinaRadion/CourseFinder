package com.ibm.coursefinder.DTOs;

import com.ibm.coursefinder.entities.Course;
import com.ibm.coursefinder.entities.CourseDetails;

import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO {
    private String name;
    private Long id;
    private List<ProfessorDTO> professorList;
    private CourseDetails courseDetails;

    public CourseDTO(Course courseDAO) {
        name = courseDAO.getName();
        courseDetails = courseDAO.getCourseDetails();
        id = courseDAO.getId();
    }

    public CourseDTO() {
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
