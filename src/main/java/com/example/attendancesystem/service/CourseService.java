package com.example.attendancesystem.service;

import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
