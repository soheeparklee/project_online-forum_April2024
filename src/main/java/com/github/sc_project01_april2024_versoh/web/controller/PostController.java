package com.github.sc_project01_april2024_versoh.web.controller;

import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.service.PostService;
import com.github.sc_project01_april2024_versoh.web.DTO.PostRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Operation(summary = "게시글 등록")
    @PostMapping("/register")
    public ResponseDTO registerPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                    @RequestBody PostRequest postRequest){
        return postService.registerPost(customUserDetails, postRequest);
    }
}
