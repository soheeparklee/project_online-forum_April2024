package com.github.sc_project01_april2024_versoh.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpa extends JpaRepository<Comment, Integer> {
}
