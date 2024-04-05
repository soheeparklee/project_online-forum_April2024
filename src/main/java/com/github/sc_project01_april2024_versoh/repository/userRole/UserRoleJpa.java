package com.github.sc_project01_april2024_versoh.repository.userRole;

import com.github.sc_project01_april2024_versoh.repository.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleJpa extends JpaRepository<UserRole, Integer> {

    List<UserRole> findByUser(User user);
}
