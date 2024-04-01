package com.github.sc_project01_april2024_versoh.repository.userRole;

import com.github.sc_project01_april2024_versoh.repository.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleJpa extends JpaRepository<UserRole, Integer> {
}
