package com.github.sc_project01_april2024_versoh.service.service;

import com.github.sc_project01_april2024_versoh.repository.post.Post;
import com.github.sc_project01_april2024_versoh.repository.post.PostJpa;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import com.github.sc_project01_april2024_versoh.repository.user.UserJpa;
import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.exceptions.NotFoundException;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.PostsResponse;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserJpa userJpa;
    private final PostJpa postJpa;
    public ResponseDTO registerPost(CustomUserDetails customUserDetails, PostRequest postRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Post post= Post.builder()
                .user(user)
                .title(postRequest.getTitle())
                .name(user.getName())
                .content(postRequest.getContent())
                .likesCount(0)  //🔴나중에 게시글 찾아올 때는 꼭 좋아요 개수 넣기 잊지말기!!!
                .createdAt(LocalDateTime.now())
                .build();
        postJpa.save(post);

        return new ResponseDTO(HttpStatus.OK.value(), "게시글이 성공적으로 작성되었습니다");
    }

    public ResponseDTO findAllPosts(Pageable pageable) {
        Page<Post> postsPage= postJpa.findAll(pageable);

        List<PostsResponse> postsResponses= postsPage.getContent()
                .stream()
                .map(post-> new PostsResponse(
                        post.getPostId(),
                        post.getTitle(),
                        post.getName(),
                        post.getLikesCount(),
                        post.getCreatedAt()))
                .collect(Collectors.toList());
        if(postsResponses.isEmpty()) throw new NotFoundException("등록된 게시글이 없습니다.");

        return new ResponseDTO(HttpStatus.OK.value(), "모든 게시글 불러오기 성공", postsResponses);
    }
}
