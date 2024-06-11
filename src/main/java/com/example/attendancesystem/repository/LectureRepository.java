package com.example.attendancesystem.repository;

import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCourseId(Long courseId);
}