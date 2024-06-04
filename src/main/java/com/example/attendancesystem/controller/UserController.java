package com.example.attendancesystem.controller;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import com.example.attendancesystem.model.AppUser;
import com.example.attendancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        // Get the current authentication from SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication is a KeycloakAuthenticationToken
        if (authentication instanceof KeycloakAuthenticationToken) {
            // Cast the authentication to KeycloakAuthenticationToken
            KeycloakAuthenticationToken keycloakAuthentication = (KeycloakAuthenticationToken) authentication;

            // Get the Keycloak security context
            KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) keycloakAuthentication.getCredentials();

            // Extract relevant information from the security context
            String accessToken = keycloakSecurityContext.getTokenString();
            // You can extract more information like username, roles, etc. as needed

            // For now, just return the access token
            return ResponseEntity.ok(accessToken);
        } else {
            // If authentication is not Keycloak, return unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated with Keycloak");
        }
    }

    @PostMapping("/register")
    public AppUser registerUser(@RequestBody AppUser user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public AppUser getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
