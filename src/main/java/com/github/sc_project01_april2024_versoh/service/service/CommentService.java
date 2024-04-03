package com.github.sc_project01_april2024_versoh.service.service;

import com.github.sc_project01_april2024_versoh.repository.comment.Comment;
import com.github.sc_project01_april2024_versoh.repository.comment.CommentJpa;
import com.github.sc_project01_april2024_versoh.repository.post.Post;
import com.github.sc_project01_april2024_versoh.repository.post.PostJpa;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import com.github.sc_project01_april2024_versoh.repository.user.UserJpa;
import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.exceptions.NotFoundException;
import com.github.sc_project01_april2024_versoh.web.DTO.comment.CommentRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserJpa userJpa;
    private final PostJpa postJpa;
    private final CommentJpa commentJpa;

    public ResponseDTO registerComment(CustomUserDetails customUserDetails, Integer postId, CommentRequest commentRequest) {

        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Post post= postJpa.findById(postId)
                .orElseThrow(()-> new NotFoundException("해당 아이디"+ postId + "를 가진 게시글을 찾지 못했습니다."));
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .name(user.getName())
                .content(commentRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        commentJpa.save(comment);

        return new ResponseDTO(HttpStatus.OK.value(), "댓글이 성공적으로 작성되었습니다.");
    }

    public ResponseDTO updateComment(CustomUserDetails customUserDetails, Integer commentId, CommentRequest commentRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Comment comment= commentJpa.findById(commentId)
                .orElseThrow(()-> new NotFoundException("해당 아이디"+ commentId + "를 가진 댓글을 찾지 못했습니다."));

        comment.setContent(commentRequest.getContent());
        commentJpa.save(comment);



        Post post= postJpa.findById(comment.getPost().getPostId())
                .orElseThrow(()-> new NotFoundException("해당 아이디를 가진 게시글을 찾지 못했습니다."));

        PostDetailResponse postDetailResponse= new PostDetailResponse(
                post.getPostId(),
                post.getTitle(),
                post.getName(),
                post.getContent(),
                post.getCreatedAt(),
                post.getCommentList());
)

        return
    }
}
