package com.github.sc_project01_april2024_versoh.web.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer commentId;
    private Integer postId;
    private String name;
    private String content;
    private LocalDateTime createdAt;

}
