package com.github.sc_project01_april2024_versoh.web.controller;

import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.service.CommentService;
import com.github.sc_project01_april2024_versoh.web.DTO.comment.CommentRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 등록")
    @PostMapping("/register/{postId}")
    public ResponseDTO registerComment(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @PathVariable Integer postId,
                                       @RequestBody CommentRequest commentRequest){
        return commentService.registerComment(customUserDetails, postId, commentRequest);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/update/{commentId}")
    public ResponseDTO updateComment(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @PathVariable Integer commentId,
                                     @RequestBody CommentRequest commentRequest){
        return commentService.updateComment(customUserDetails, commentId, commentRequest);
    }
    @Operation(summary = "댓글 삭제")
    @DeleteMapping ("/delete/{commentId}")
    public ResponseDTO deleteComment(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                     @PathVariable Integer commentId){
        return commentService.deleteComment(customUserDetails, commentId);
    }
}
