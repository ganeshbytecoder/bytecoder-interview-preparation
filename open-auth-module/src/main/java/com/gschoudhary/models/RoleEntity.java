package com.gschoudhary.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


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
    private List<RolePermissionsEntity> permissions;


    public RoleEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
