package com.gschoudhary.Bytecoder;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class UserDto {
    @NotEmpty
    private String name;

    private int age;

    private String role;
}
