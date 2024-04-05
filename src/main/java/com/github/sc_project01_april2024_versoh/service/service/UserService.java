package com.github.sc_project01_april2024_versoh.service.service;

import com.github.sc_project01_april2024_versoh.repository.comment.Comment;
import com.github.sc_project01_april2024_versoh.repository.comment.CommentJpa;
import com.github.sc_project01_april2024_versoh.repository.post.Post;
import com.github.sc_project01_april2024_versoh.repository.post.PostJpa;
import com.github.sc_project01_april2024_versoh.repository.role.Role;
import com.github.sc_project01_april2024_versoh.repository.role.RoleJpa;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import com.github.sc_project01_april2024_versoh.repository.user.UserJpa;
import com.github.sc_project01_april2024_versoh.repository.userDetails.CustomUserDetails;
import com.github.sc_project01_april2024_versoh.repository.userRole.UserRole;
import com.github.sc_project01_april2024_versoh.repository.userRole.UserRoleJpa;
import com.github.sc_project01_april2024_versoh.service.exceptions.NotFoundException;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import com.github.sc_project01_april2024_versoh.web.DTO.comment.CommentDto;
import com.github.sc_project01_april2024_versoh.web.DTO.post.PostsResponse;
import com.github.sc_project01_april2024_versoh.web.DTO.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpa userJpa;
    private final UserRoleJpa userRoleJpa;
    private final RoleJpa roleJpa;
    private final PostJpa postJpa;
    private final CommentJpa commentJpa;
    public ResponseDTO findMyPage(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        UserResponse userResponse = UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .userRole(user.getUserRole())
                .build();
        return new ResponseDTO(HttpStatus.OK.value(), "User information get success", userResponse);
    }

    public ResponseDTO findMyPost(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        List<Post> posts= postJpa.findByUser(user);
        List<PostsResponse> postsResponseList = posts
                .stream()
                .map(p-> new PostsResponse(
                        p.getPostId(),
                        p.getTitle(),
                        p.getName(),
                        p.getLikesCount(),
                        p.getCreatedAt()))
                .collect(Collectors.toList());
        if(postsResponseList.isEmpty()) throw new NotFoundException("유저가 작성한 게시글이 없습니다.");

        return new ResponseDTO(HttpStatus.OK.value(), "내가 작성한 게시글 모두 불러오기 성공", postsResponseList);

    }

    public ResponseDTO findMyComment(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        List<Comment> comments= commentJpa.findByUser(user);
        List<CommentDto> commentDtoList= comments
                .stream()
                .map(c-> new CommentDto(
                        c.getCommentId(),
                        c.getPost().getPostId(),
                        c.getName(),
                        c.getContent(),
                        c.getCreatedAt()
                )).collect(Collectors.toList());
        if(commentDtoList.isEmpty()) throw new NotFoundException("유저가 작성한 댓글이 없습니다.");
        return new ResponseDTO(HttpStatus.OK.value(), "내가 작성한 댓글 모두 불러오기 성공", commentDtoList);

    }

    public ResponseDTO changeUserRole(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        List<UserRole> userRoleList= userRoleJpa.findByUser(user);
        UserRole userRole= userRoleList.get(0);
        Role role= userRole.getRole();
        String roleName= role.getName();

        userRoleJpa.delete(userRole);

        Role newRole= roleJpa.findByName(roleName.equals("ROLE_USER") ?  "ROLE_ADMIN": "ROLE_USER");

        userRole.setRole(newRole);
        userRoleJpa.save(userRole);



        UserResponse userResponse = UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .userRole(user.getUserRole())
                .build();

        return new ResponseDTO(HttpStatus.OK.value(), "User role change success", userResponse);
    }
}
