package com.gschoudhary.models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PERMISSIONS")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private RoleEntity roleEntity;

}
