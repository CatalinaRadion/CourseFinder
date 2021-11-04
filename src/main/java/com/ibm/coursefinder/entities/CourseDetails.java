package com.ibm.coursefinder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Entity
public class CourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "courseDetails")
    private String courseDetails;

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
                oos.writeObject(courseDetails.toString());
    }
    @Override
    public String toString() {
        return "CourseDetails{" +
                "courseDetails='" + courseDetails + '\'' +
                '}';
    }
}
