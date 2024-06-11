package com.example.attendancesystem.repository;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByCourseId(Long courseId);
    boolean existsByCourseIdAndUserId(Long courseId, Long userId);
    List<Enrollment> findByUserId(Long userId);
    List<AppUser> findUsersByCourseId(@Param("courseId") Long courseId);
    // You can add custom query methods if needed
}
