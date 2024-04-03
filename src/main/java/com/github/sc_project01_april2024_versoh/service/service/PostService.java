package com.github.sc_project01_april2024_versoh.service.service;

import com.github.sc_project01_april2024_versoh.repository.comment.Comment;
import com.github.sc_project01_april2024_versoh.repository.comment.CommentJpa;
import com.github.sc_project01_april2024_versoh.repository.post.Post;
import com.github.sc_project01_april2024_versoh.repository.post.PostJpa;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import com.github.sc_project01_april2024_versoh.repository.user.UserJpa;
import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.service.exceptions.NotFoundException;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostDetailResponse;
import com.github.sc_project01_april2024_versoh.web.DTO.comment.CommentDto;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostsResponse;
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
    private final CommentJpa commentJpa;
    public ResponseDTO registerPost(CustomUserDetails customUserDetails, PostRequest postRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Post post= Post.builder()
                .user(user)
                .title(postRequest.getTitle())
                .name(user.getName())
                .content(postRequest.getContent())
                .likesCount(0)
                .createdAt(LocalDateTime.now())
                .build();
        postJpa.save(post);

        return new ResponseDTO(HttpStatus.OK.value(), "게시글이 성공적으로 작성되었습니다");
    }

    public ResponseDTO findAllPosts(Pageable pageable) {
        Page<Post> postsPage= postJpa.findAll(pageable);

        List<PostsResponse> postsResponseList= postsPage.getContent()
                .stream()
                .map(post-> new PostsResponse(
                        post.getPostId(),
                        post.getTitle(),
                        post.getName(),
                        post.getLikesCount(),
                        post.getCreatedAt()))
                .collect(Collectors.toList());
        if(postsResponseList.isEmpty()) throw new NotFoundException("등록된 게시글이 없습니다.");

        return new ResponseDTO(HttpStatus.OK.value(), "모든 게시글 불러오기 성공", postsResponseList);
    }

    public ResponseDTO findPostById(Integer postId) {
        Post post= postJpa.findById(postId)
                .orElseThrow(()-> new NotFoundException("아이디 "+ postId +"에 해당하는 게시글이 없습니다."));
        List<Comment> comments= commentJpa.findByPost(post);
        List<CommentDto> commentDtoList= comments
                .stream()
                .map(c-> new CommentDto(
                        c.getCommentId(),
                        c.getPost().getPostId(),
                        c.getName(),
                        c.getContent(),
                        c.getCreatedAt()))
                .collect(Collectors.toList());

        PostDetailResponse postDetailResponse= PostDetailResponse
                .builder()
                .postId(postId)
                .title(post.getTitle())
                .name(post.getName())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .commentDtoList(commentDtoList)
                .build();

        return new ResponseDTO(HttpStatus.OK.value(), "게시글 상세 조회 성공", postDetailResponse);
    }

    public ResponseDTO findPostByKeyword(String keyword) {
        String newKeyword= keyword.toLowerCase();
        List<Post> posts= postJpa.findAll();
        List<PostsResponse> postsResponseList= posts
                .stream()
                .filter(p-> p.getContent().toLowerCase().contains(newKeyword) || p.getTitle().toLowerCase().contains(newKeyword))
                .map(p-> new PostsResponse(
                        p.getPostId(),
                        p.getTitle(),
                        p.getName(),
                        p.getLikesCount(),
                        p.getCreatedAt()))
                .toList();
        if(postsResponseList.isEmpty()) throw new NotFoundException("No post with keyword: "+ keyword);

        return new ResponseDTO(HttpStatus.OK.value(),"Post with keyword <"+ keyword + "> found!", postsResponseList);

    }

    public ResponseDTO updatePost(CustomUserDetails customUserDetails, Integer postId, PostRequest postRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Post post= postJpa.findById(postId)
                .orElseThrow(()-> new NotFoundException("아이디 "+ postId +"에 해당하는 게시글이 없습니다."));

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        Post updatePost= postJpa.save(post);

        PostDetailResponse postDetailResponse= new PostDetailResponse(
                updatePost.getPostId(),
                updatePost.getTitle(),
                updatePost.getName(),
                updatePost.getContent());

        return new ResponseDTO(HttpStatus.OK.value(), "Post updated successfully", postDetailResponse);
    }

    public ResponseDTO deletePost(CustomUserDetails customUserDetails, Integer postId) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Post post= postJpa.findById(postId)
                .orElseThrow(()-> new NotFoundException("아이디 "+ postId +"에 해당하는 게시글이 없습니다."));

        postJpa.delete(post);

        return new ResponseDTO(HttpStatus.OK.value(), "Post deleted successfully");
    }

    public ResponseDTO addLikes(CustomUserDetails customUserDetails, Integer postId) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Post post= postJpa.findById(postId)
                .orElseThrow(()-> new NotFoundException("아이디 "+ postId +"에 해당하는 게시글이 없습니다."));

        post.setLikesCount(post.getLikesCount() + 1);
        Post updatePost= postJpa.save(post);

        PostsResponse postsResponse = new PostsResponse(
                updatePost.getPostId(),
                updatePost.getTitle(),
                updatePost.getName(),
                updatePost.getLikesCount(),
                updatePost.getCreatedAt());


        return new ResponseDTO(HttpStatus.OK.value(),"Like successfully added. Post number "+ postId + " has " +post.getLikesCount() + " likes", postsResponse);
    }



}

