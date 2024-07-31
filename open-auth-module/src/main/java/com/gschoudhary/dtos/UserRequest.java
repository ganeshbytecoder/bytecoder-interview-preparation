package com.gschoudhary.dtos;

import com.gschoudhary.models.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotEmpty
    private Set<RoleEntity> roles;

}
