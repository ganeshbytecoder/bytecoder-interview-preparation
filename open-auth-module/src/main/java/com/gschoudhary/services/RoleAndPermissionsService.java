package com.gschoudhary.services;

import com.gschoudhary.dtos.ResponseDto;
import com.gschoudhary.dtos.RoleAndPermissionsDto;
import com.gschoudhary.models.RoleEntity;

import java.util.List;

public interface RoleAndPermissionsService {


    ResponseDto<RoleAndPermissionsDto> deleteRole(RoleAndPermissionsDto roleAndPermissionsDto);

    ResponseDto<RoleAndPermissionsDto> saveRole(RoleAndPermissionsDto roleAndPermissionsDto);

    ResponseDto<List<RoleEntity>> getAllRoles();

    ResponseDto<RoleAndPermissionsDto> updateRole(RoleAndPermissionsDto roleAndPermissionsDto);
}
