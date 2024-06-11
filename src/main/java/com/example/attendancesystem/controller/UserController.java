package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Enrollment;
import com.example.attendancesystem.service.EnrollmentService;
import com.example.attendancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping ("/")
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public AppUser addUser(@RequestBody AppUser user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<AppUser> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("role/{role}")
    public List<AppUser> getUserByRole(@PathVariable String role) {
        // Call the userService method to fetch users by role
        return userService.getUserByRole(role);
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollUserInCourse(@RequestParam Long courseId, @RequestParam Long userId) {
        try {
            Enrollment enrolledUser = enrollmentService.enrollUserInCourse(courseId, userId);
            return ResponseEntity.ok(enrolledUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to enroll user: " + e.getMessage());
        }
    }


}
