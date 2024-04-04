package com.github.sc_project01_april2024_versoh.repository.post;

import com.github.sc_project01_april2024_versoh.repository.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostJpa extends JpaRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);

    List<Post> findByUser(User user);


//    @Query("SELECT p FROM Post p")
//    Page<Post> findAllPosts(Pageable pageable);
}
