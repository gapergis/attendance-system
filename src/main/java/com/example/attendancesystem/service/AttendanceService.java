package com.example.attendancesystem.service;

import com.example.attendancesystem.model.Attendance;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Lecture;
import com.example.attendancesystem.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LectureService lectureService;

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }



    public void createAttendances(Long courseId, Long studentId) {
        List<Lecture> lectures = lectureService.getLecturesByCourseId(courseId);
        for (Lecture lecture: lectures){
            Attendance attendance = new Attendance();
            attendance.setLectureId(lecture.getLecture_id());
            attendance.setStudentId(studentId);
            attendance.setStatus("N/A");
            attendance.setConfirmation("unconfirmed");
            attendanceRepository.save(attendance);
        }
    }

    public List<Attendance> getAllAttendances() {
        // Call a method from your repository to retrieve all attendances
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceByLectureId(Long lectureId) {
        return attendanceRepository.findById(lectureId);
    }

    public List<Attendance> getAllAttendancesByLectureId(Long lectureId) {
        return attendanceRepository.findByLectureId(lectureId);
    }

    public Attendance toggleConfirmation(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new RuntimeException("Attendance not found"));
        if ("confirmed".equals(attendance.getConfirmation())) {
            attendance.setConfirmation("unconfirmed");
        } else {
            attendance.setConfirmation("confirmed");
        }
        return attendanceRepository.save(attendance);
    }
    public void updateAttendanceStatus(Long attendanceId, String newStatus) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found"));
        attendance.setStatus(newStatus);
        attendanceRepository.save(attendance);
    }
}
