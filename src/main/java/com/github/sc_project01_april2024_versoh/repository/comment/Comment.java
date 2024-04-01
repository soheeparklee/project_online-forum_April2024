package com.github.sc_project01_april2024_versoh.repository.comment;

import com.github.sc_project01_april2024_versoh.repository.post.Post;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="commentId")
@Table(name= "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "comment_id")
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name= "post_id", nullable = false)
    private Post post;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "content", nullable = false)
    private String content;

    @Column(name= "create_at")
    private LocalDateTime createdAt;



}
