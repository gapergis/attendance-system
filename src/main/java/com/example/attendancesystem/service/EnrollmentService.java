package com.example.attendancesystem.service;

import com.example.attendancesystem.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.attendancesystem.repository.EnrollmentRepository;
import java.util.List;


@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Method to fetch enrollments by user ID
    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public boolean isUserEnrolled(Long courseId, Long userId) {
        return enrollmentRepository.existsByCourseIdAndUserId(courseId, userId);
    }

    public Enrollment enrollUserInCourse(Long courseId, Long userId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(courseId);
        enrollment.setUserId(userId);
        return enrollmentRepository.save(enrollment);
    }

    // Other methods related to enrollment management
}
