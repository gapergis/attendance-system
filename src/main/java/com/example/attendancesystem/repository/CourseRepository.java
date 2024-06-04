package com.example.attendancesystem.repository;

import com.example.attendancesystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
