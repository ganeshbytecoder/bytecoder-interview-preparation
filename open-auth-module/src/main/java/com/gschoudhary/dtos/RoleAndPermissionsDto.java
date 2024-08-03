package com.gschoudhary.dtos;

import com.gschoudhary.models.PermissionEntity;
import lombok.Data;

import java.util.List;

@Data
public class RoleAndPermissionsDto {

    private String title;
    private String description;
    private List<PermissionEntity> permissions;


}
