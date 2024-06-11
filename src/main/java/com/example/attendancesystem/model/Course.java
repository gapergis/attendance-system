package com.example.attendancesystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    private Long professorId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "courseId", cascade = CascadeType.ALL)
    private List<Lecture> courseLectures;

    @ManyToMany
    @JoinTable(
            name = "enrolled_users",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> enrolledUsers;

    public List<Lecture> getCourseLectures() {
        return courseLectures;
    }


    public List<AppUser> getEnrolledUsers() {
        return enrolledUsers;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }


    // Other methods
}
