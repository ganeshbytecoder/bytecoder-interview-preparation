package com.gschoudhary.services;

import com.gschoudhary.dtos.ResponseDto;
import com.gschoudhary.dtos.RoleAndPermissionsDto;
import com.gschoudhary.models.RoleEntity;
import com.gschoudhary.models.RolePermissionsEntity;
import com.gschoudhary.repositories.RolePermissionsRepository;
import com.gschoudhary.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAndPermissionsServiceImpl implements RoleAndPermissionsService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionsRepository rolePermissionsRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public ResponseDto<RoleAndPermissionsDto> saveRole(RoleAndPermissionsDto roleAndPermissionsDto) {

        RoleEntity roleEntity = roleRepository.findByTitle(roleAndPermissionsDto.getTitle()).orElseGet(
                () -> {
                    return roleRepository.save(new RoleEntity(roleAndPermissionsDto.getTitle(), roleAndPermissionsDto.getDescription()));
                }
        );

        for (RolePermissionsEntity rolePermissionsEntity : roleAndPermissionsDto.getPermissions()) {
            rolePermissionsEntity.setRoleEntity(roleEntity);
            rolePermissionsRepository.save(rolePermissionsEntity);
        }

        ResponseDto<RoleAndPermissionsDto> userRoleEntityResponseDto = new ResponseDto<>();
        userRoleEntityResponseDto.setMessage("Successfully saved.");
        userRoleEntityResponseDto.setStatus(200);
        userRoleEntityResponseDto.setResponse(roleAndPermissionsDto);
        return userRoleEntityResponseDto;
    }

    @Override
    public ResponseDto<List<RoleEntity>> getAllRoles() {
        List<RoleEntity> userRoleEntities = (List<RoleEntity>) roleRepository.findAll();

        ResponseDto<List<RoleEntity>> userRoleEntityResponseDto = new ResponseDto<>();
        userRoleEntityResponseDto.setMessage("Successfully fetched.");
        userRoleEntityResponseDto.setStatus(200);
        userRoleEntityResponseDto.setResponse(userRoleEntities);
        return userRoleEntityResponseDto;
    }

    @Override
    public ResponseDto<RoleAndPermissionsDto> updateRole(RoleAndPermissionsDto roleAndPermissionsDto) {
        RoleEntity roleEntity = roleRepository.findByTitle(roleAndPermissionsDto.getTitle()).orElseThrow(() -> new RuntimeException("Role is not found."));
        rolePermissionsRepository.deleteAllWithRole(roleEntity.getId());
        for (RolePermissionsEntity rolePermissionsEntity : roleAndPermissionsDto.getPermissions()) {
            rolePermissionsEntity.setRoleEntity(roleEntity);
            rolePermissionsRepository.save(rolePermissionsEntity);
        }

        ResponseDto<RoleAndPermissionsDto> userRoleEntityResponseDto = new ResponseDto<>();
        userRoleEntityResponseDto.setMessage("Successfully updated.");
        userRoleEntityResponseDto.setStatus(200);
        userRoleEntityResponseDto.setResponse(roleAndPermissionsDto);
        return userRoleEntityResponseDto;
    }

    @Override
    public ResponseDto<RoleAndPermissionsDto> deleteRole(RoleAndPermissionsDto roleAndPermissionsDto) {
        RoleEntity roleEntity = roleRepository.findByTitle(roleAndPermissionsDto.getTitle()).orElseThrow(() -> new RuntimeException("Role is not found."));
        rolePermissionsRepository.deleteAllWithRole(roleEntity.getId());
        roleRepository.delete(roleEntity);
        ResponseDto<RoleAndPermissionsDto> userRoleEntityResponseDto = new ResponseDto<>();
        userRoleEntityResponseDto.setMessage("Successfully deleted.");
        userRoleEntityResponseDto.setStatus(200);
        userRoleEntityResponseDto.setResponse(roleAndPermissionsDto);
        return userRoleEntityResponseDto;
    }
}
