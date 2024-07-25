package com.gschoudhary.open2api.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {
    private String name;

    private int age;

    private String role;
}
