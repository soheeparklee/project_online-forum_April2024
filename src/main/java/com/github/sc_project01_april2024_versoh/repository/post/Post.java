package com.github.sc_project01_april2024_versoh.repository.post;

import com.github.sc_project01_april2024_versoh.repository.comment.Comment;
import com.github.sc_project01_april2024_versoh.repository.likes.Likes;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="postId")
@Table(name= "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "post_id")
    private Integer postId;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @Column(name= "title", nullable = false)
    private String title;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "content", nullable = false)
    private String content;

    @Column(name= "like_count")
    private Integer likeCount;

    @Column(name= "create_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Likes> likesList;
}
