package com.example.attendancesystem.service;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Course;
import com.example.attendancesystem.model.Enrollment;
import com.example.attendancesystem.model.Lecture;
import com.example.attendancesystem.repository.CourseRepository;
import com.example.attendancesystem.repository.EnrollmentRepository;
import com.example.attendancesystem.repository.UserRepository;
import com.example.attendancesystem.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    public AppUser saveUser(AppUser user) {
        // Implement the logic to save the user to the database
        // For example:
        return userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        // Call a method from your repository to retrieve all lectures
        return userRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        // Call a method from your repository to retrieve all lectures
        return userRepository.findById(id);
    }

    public List<AppUser> getUserByRole(String role) {
        return userRepository.findByRole(role);
    }

    public List<AppUser> getUsersEnrolledInLecture(Long lectureId) {
        Optional<Lecture> lectureOpt = lectureRepository.findById(lectureId);
        if (lectureOpt.isPresent()) {
            Lecture lecture = lectureOpt.get();
            return courseRepository.findByCourseId(lecture.getCourse()).getEnrolledUsers();
        } else return null;
    }

    public List<AppUser> getUsersEnrolledInCourse(Long courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            return courseRepository.findByCourseId(course.getCourseId()).getEnrolledUsers();
        } else return null;
    }


}
