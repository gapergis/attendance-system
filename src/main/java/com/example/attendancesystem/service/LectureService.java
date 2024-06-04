package com.example.attendancesystem.service;

import com.example.attendancesystem.model.Lecture;
import com.example.attendancesystem.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;


    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }
}
