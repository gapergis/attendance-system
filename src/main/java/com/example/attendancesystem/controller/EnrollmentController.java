package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Enrollment;
import com.example.attendancesystem.service.AttendanceService;
import com.example.attendancesystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
@CrossOrigin(origins = "http://frontend:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/check")
    public boolean checkEnrollment(@RequestParam Long courseId, @RequestParam Long userId) {
        return enrollmentService.isUserEnrolled(courseId, userId);
    }

    @PostMapping("/enroll")
    public Enrollment enrollUser(@RequestBody Enrollment enrollment) {
        return enrollmentService.enrollUserInCourse(enrollment.getCourseId(), enrollment.getUserId());
    }

    @PostMapping("/createAttendances")
    public ResponseEntity<String> createAttendances(@RequestBody Enrollment enrollment) {
        try {
            attendanceService.createAttendances(enrollment.getCourseId(), enrollment.getUserId());
            return ResponseEntity.ok("Attendance records created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating attendance records: " + e.getMessage());
        }
    }
}

