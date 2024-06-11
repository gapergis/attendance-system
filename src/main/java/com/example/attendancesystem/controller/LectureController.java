package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Lecture;
import com.example.attendancesystem.repository.LectureRepository;
import com.example.attendancesystem.service.UserService;
import com.example.attendancesystem.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/lectures")
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private UserService userService;

    @PutMapping("/create")
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        Lecture createdLecture = lectureService.createLecture(lecture);
        return ResponseEntity.ok(createdLecture);
    }

    @GetMapping ("/")
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }

    @GetMapping("/lecture/{lectureId}")
    public Optional<Lecture> getLectureById(@PathVariable Long lectureId) {
        return lectureService.getLectureById(lectureId);
    }

    @GetMapping("/course/{courseId}")
    public List<Lecture> getLecturesByCourseId(@PathVariable Long courseId) {
        return lectureService.getLecturesByCourseId(courseId);
    }

    @GetMapping("/{lectureId}/enrolled-users")
    public List<AppUser> getUsersEnrolledInLecture(@PathVariable Long lectureId) {
        return userService.getUsersEnrolledInLecture(lectureId);
    }
    // Other endpoints for updating and deleting lectures can be added here
}