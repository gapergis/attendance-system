package com.example.attendancesystem.repository;

import com.example.attendancesystem.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {}