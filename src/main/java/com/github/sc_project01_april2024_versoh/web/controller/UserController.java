package com.github.sc_project01_april2024_versoh.web.controller;

import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.service.UserService;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "가입 정보 조회")
    @GetMapping("/my-page")
    public ResponseDTO findMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return userService.findMyPage(customUserDetails);
    }

//    @Operation(summary = "Role 바꾸기")
//    @PutMapping("/change-role")
//    public ResponseDTO changeUserRole(@AuthenticationPrincipal CustomUserDetails customUserDetails){
//        return userService.changeUserRole(customUserDetails);
//    }



    @Operation(summary = "내가 작성한 게시글 조회")
    @GetMapping("/my-post")
    public ResponseDTO findMyPost(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return userService.findMyPost(customUserDetails);
    }


    @Operation(summary = "내가 작성한 댓글 조회")
    @GetMapping("/my-comment")
    public ResponseDTO findMyComment(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return userService.findMyComment(customUserDetails);
    }
}
