package com.github.sc_project01_april2024_versoh.repository.userRole;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sc_project01_april2024_versoh.repository.role.Role;
import com.github.sc_project01_april2024_versoh.repository.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Integer userRoleId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
