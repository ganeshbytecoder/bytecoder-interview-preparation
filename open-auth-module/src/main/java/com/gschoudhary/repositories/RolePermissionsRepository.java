package com.gschoudhary.repositories;

import com.gschoudhary.models.PermissionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RolePermissionsRepository extends CrudRepository<PermissionEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM PermissionEntity u WHERE u.roleEntity.id = :id")
    void deleteAllWithRole(long id);
}
