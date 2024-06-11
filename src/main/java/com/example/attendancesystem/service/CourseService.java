package com.example.attendancesystem.service;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Enrollment;
import com.example.attendancesystem.repository.CourseRepository;
import com.example.attendancesystem.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        // Call a method from your repository to retrieve all courses
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public List<Course> getCoursesForStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUserId(studentId);
        // Extract course IDs from enrollments
        List<Long> courseIds = enrollments.stream()
                .map(Enrollment::getCourseId)
                .collect(Collectors.toList());
        // Fetch courses based on course IDs
        return courseRepository.findAllById(courseIds);
    }


    public List<AppUser> getEnrolledUsersByCourseId(Long courseId) {
        return enrollmentRepository.findUsersByCourseId(courseId);
    }
}
