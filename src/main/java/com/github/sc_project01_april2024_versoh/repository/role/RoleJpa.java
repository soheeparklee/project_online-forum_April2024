package com.github.sc_project01_april2024_versoh.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpa extends JpaRepository<Role, Integer> {
    Role findByName(String roleUser);
}
