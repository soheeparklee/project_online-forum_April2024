package com.github.sc_project01_april2024_versoh.repository.comment;

import com.github.sc_project01_april2024_versoh.repository.post.Post;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentJpa extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
