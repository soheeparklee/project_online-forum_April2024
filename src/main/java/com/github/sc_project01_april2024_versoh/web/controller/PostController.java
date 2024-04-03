package com.github.sc_project01_april2024_versoh.web.controller;

import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.service.PostService;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "게시글 조회")
    @GetMapping("/findAll")
    public ResponseDTO findAllPosts(Pageable pageable){
        return postService.findAllPosts(pageable);
    }

    @Operation(summary = "게시글 상세 조회")
    @GetMapping("/find/id/{postId}")
    public ResponseDTO findPostById(@PathVariable Integer postId){
        return postService.findPostById(postId);
    }

    @Operation(summary = "키워드로 게시글 조회")
    @GetMapping("/find/keyword/{keyword}")
    public ResponseDTO findPostByKeyword(@PathVariable String keyword){
        return postService.findPostByKeyword(keyword);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/update/{postId}")
    public ResponseDTO updatePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @PathVariable Integer postId,
                                  @RequestBody PostRequest postRequest){
        return postService.updatePost(customUserDetails, postId, postRequest);
    }

    @Operation(summary = "좋아요 누르기")
    @PostMapping("/likes/{postId}")
    public ResponseDTO addLikes(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                @PathVariable Integer postId){
        return postService.addLikes(customUserDetails, postId);
    }

}
