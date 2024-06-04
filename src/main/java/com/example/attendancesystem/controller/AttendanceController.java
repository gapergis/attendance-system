package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Lecture;
import com.example.attendancesystem.model.Attendance;
import com.example.attendancesystem.service.AttendanceService;
import com.example.attendancesystem.service.CourseService;
import com.example.attendancesystem.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LectureService lectureService;


    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PostMapping("/lectures")
    public Lecture createLecture(@RequestBody Lecture lecture) {
        return lectureService.createLecture(lecture);
    }

    @PostMapping("/attendance")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/attendance/{lectureId}")
    public List<Attendance> getAttendanceForLecture(@PathVariable Long lectureId) {
        return attendanceService.getAttendanceForLecture(lectureId);
    }
}
