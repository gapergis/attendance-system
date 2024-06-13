package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.service.CourseService;
import com.example.attendancesystem.repository.EnrollmentRepository;
import com.example.attendancesystem.repository.CourseRepository;
import com.example.attendancesystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping ("/")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Optional<Course> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/{courseId}/enrolled-users")
    public List<AppUser> getUsersEnrolledInCourse(@PathVariable Long courseId) {
        return userService.getUsersEnrolledInCourse(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<Course> getCoursesForStudent(@PathVariable Long studentId) {
        return courseService.getCoursesForStudent(studentId);
    }
    // Other endpoints for updating and deleting courses can be added here
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }
}