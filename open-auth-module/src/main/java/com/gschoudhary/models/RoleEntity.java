package com.gschoudhary.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "userRoleEntity")
    private List<RolePermissionsEntity> permissions;

    public RoleEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
