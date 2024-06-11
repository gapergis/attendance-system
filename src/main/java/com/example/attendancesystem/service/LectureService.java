package com.example.attendancesystem.service;

import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Lecture;
import com.example.attendancesystem.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;


    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public List<Lecture> getAllLectures() {
        // Call a method from your repository to retrieve all lectures
        return lectureRepository.findAll();
    }

    public Optional<Lecture> getLectureById(Long lectureId) {
        return lectureRepository.findById(lectureId);
    }

    public List<Lecture> getLecturesByCourseId(Long courseId) {
        return lectureRepository.findByCourseId(courseId);
    }
}
