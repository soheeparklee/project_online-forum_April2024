package com.github.sc_project01_april2024_versoh.web.DTO.user;

import com.github.sc_project01_april2024_versoh.repository.userRole.UserRole;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Integer userId;
    private String email;
    private String name;
    private String phoneNumber;
    private List<UserRole> userRole;
}
