package com.github.sc_project01_april2024_versoh.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponse {
    private Integer postId;
    private String title;
    private String name;
    private Integer likesCount;
    private LocalDateTime createdAt;

    public PostsResponse(Integer postId, String title, String name, Long likesCount, LocalDateTime createdAt) {
        this.postId = postId;
        this.title = title;
        this.name = name;
        this.likesCount = likesCount != null ? likesCount.intValue() : 0;
        this.createdAt = createdAt;
    }
}
