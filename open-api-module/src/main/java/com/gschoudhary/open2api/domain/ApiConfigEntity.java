package com.gschoudhary.open2api.domain;

import com.gschoudhary.open2api.enums.AuthType;
import com.gschoudhary.open2api.enums.MethodType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ApiConfigEntity")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String apiUrl;

    @Enumerated(EnumType.STRING)
    private MethodType methodType;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    private String mediaType;

    private String jsonObject;

    @Setter(AccessLevel.NONE)
    private String apiUniqueCode;

    private String authApiCode;

    private String apiCode;

    // using which tech it will call (sync/kafka/ reactive etc)
    private String RequestType;

    // after success of current api this api id will be called 
    private String callBackApiId;


    
}
