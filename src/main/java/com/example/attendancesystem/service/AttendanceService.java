package com.example.attendancesystem.service;

import com.example.attendancesystem.model.Attendance;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.repository.AttendanceRepository;
import com.example.attendancesystem.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForLecture(Long lectureId) {
        return attendanceRepository.findByLectureId(lectureId);
    }
}
