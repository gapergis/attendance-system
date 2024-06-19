package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.Attendance;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.repository.AttendanceRepository;
import com.example.attendancesystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attendances")
@CrossOrigin(origins = "http://frontend:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;


    @PutMapping("/create")
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        Attendance createdAttendance = attendanceService.createAttendance(attendance);
        return ResponseEntity.ok(createdAttendance);
    }

    @PostMapping ("/add")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping ("/")
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/lecture/{lectureId}/student/{studentId}")
    public List<Attendance> getAttendanceByLectureAndStudent(@PathVariable Long lectureId, @PathVariable Long studentId) {
        return attendanceRepository.findByLectureIdAndStudentId(lectureId, studentId);
    }

    @GetMapping("/lecture/{lectureId}")
    public Optional<Attendance> getAttendanceByLectureId(@PathVariable Long lectureId) {
        return attendanceService.getAttendanceByLectureId(lectureId);
    }

    @GetMapping("/lectures/{lectureId}")
    public ResponseEntity<List<Attendance>> getAllAttendancesByLectureId(@PathVariable Long lectureId) {
        List<Attendance> attendanceList = attendanceService.getAllAttendancesByLectureId(lectureId);
        if (attendanceList != null && !attendanceList.isEmpty()) {
            return ResponseEntity.ok(attendanceList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{attendanceId}/toggle")
    public ResponseEntity<Attendance> toggleConfirmation(@PathVariable Long attendanceId) {
        try {
            Attendance updatedAttendance = attendanceService.toggleConfirmation(attendanceId);
            return ResponseEntity.ok(updatedAttendance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{attendanceId}/{status}")
    public ResponseEntity<?> updateAttendanceStatus(@PathVariable Long attendanceId, @PathVariable String status) {
        try {
            attendanceService.updateAttendanceStatus(attendanceId, status);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update attendance status: " + e.getMessage());
        }
    }
}