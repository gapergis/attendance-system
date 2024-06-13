package com.example.attendancesystem.repository;

import com.example.attendancesystem.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByRole(String role);
    AppUser findByUsername(String username);
}
