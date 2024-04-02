package com.github.sc_project01_april2024_versoh.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponse {
    private Integer postId;
    private String title;
    private String name;
    private LocalDateTime createdAt;
    private List<CommentDto> commentDtoList;


}
