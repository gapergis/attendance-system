package com.example.attendancesystem.controller;

import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.model.Enrollment;
import com.example.attendancesystem.repository.UserRepository;
import com.example.attendancesystem.service.EnrollmentService;
import com.example.attendancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://frontend:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping ("/")
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public AppUser addUser(@RequestBody AppUser user) {
        return userService.saveUser(user);
    }

    @GetMapping("/byId/{id}")
    public Optional<AppUser> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        AppUser user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
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
