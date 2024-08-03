package com.gschoudhary.controllers;



import com.gschoudhary.dtos.ResponseDto;
import com.gschoudhary.dtos.RoleAndPermissionsDto;
import com.gschoudhary.models.RoleEntity;
import com.gschoudhary.services.RoleAndPermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/api/v1")
public class RoleAndPermissionsRestController {

    @Autowired
    RoleAndPermissionsService roleAndPermissionsService;

    private final Logger logger = LoggerFactory.getLogger("RoleAndPermissionsRestController");


    @PostMapping(value = "/roles")
    public ResponseEntity saveRole(@RequestBody RoleAndPermissionsDto roleAndPermissionsDto) {
        try {
            logger.info(String.format("Creating new role %s ", roleAndPermissionsDto.getTitle()));
            ResponseDto<RoleAndPermissionsDto> response = roleAndPermissionsService.saveRole(roleAndPermissionsDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity getAllRoles() {
        try {
            ResponseDto<List<RoleEntity>> response = roleAndPermissionsService.getAllRoles();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/roles")
    public ResponseEntity updateRole(@RequestBody RoleAndPermissionsDto roleAndPermissionsDto) {
        try {
            ResponseDto<RoleAndPermissionsDto> response = roleAndPermissionsService.updateRole(roleAndPermissionsDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/roles")
    public ResponseEntity deleteRole(@RequestBody RoleAndPermissionsDto roleAndPermissionsDto) {
        try {
            ResponseDto<RoleAndPermissionsDto> response = roleAndPermissionsService.deleteRole(roleAndPermissionsDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
