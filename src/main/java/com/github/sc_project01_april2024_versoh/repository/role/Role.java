package com.github.sc_project01_april2024_versoh.repository.role;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of= "roleId")
@Table(name= "role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "role_id")
    private Integer roleId;

    @Column(name= "name", nullable = false)
    private String name;
}
