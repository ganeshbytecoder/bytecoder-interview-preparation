package com.gschoudhary.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ROLES")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;


    private String title;

    private String description;

    @OneToMany(mappedBy = "roleEntity")
    private List<PermissionEntity> permissions;


    public RoleEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
