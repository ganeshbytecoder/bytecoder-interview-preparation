package com.gschoudhary.models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PERMISSIONS")
public class RolePermissionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="id")
    @JsonIgnore
    private RoleEntity roleEntity;

}
