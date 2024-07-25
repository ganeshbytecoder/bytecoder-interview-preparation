package com.gschoudhary.open2api.domain;

import com.gschoudhary.open2api.enums.AuthType;
import com.gschoudhary.open2api.enums.MethodType;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String apiCode;

    private String apiUrl;

    @Enumerated(EnumType.STRING)
    private MethodType methodType;

    private String mediaType;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    private String jsonObject;
}
