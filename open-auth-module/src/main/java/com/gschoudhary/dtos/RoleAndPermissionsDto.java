package com.gschoudhary.dtos;

import com.gschoudhary.models.RolePermissionsEntity;
import lombok.Data;

import java.util.List;

@Data
public class RoleAndPermissionsDto {

    private String title;
    private String description;
    private List<RolePermissionsEntity> permissions;


}
