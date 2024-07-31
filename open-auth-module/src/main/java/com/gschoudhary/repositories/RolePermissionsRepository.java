package com.gschoudhary.repositories;

import com.gschoudhary.models.RolePermissionsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RolePermissionsRepository extends CrudRepository<RolePermissionsEntity, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RolePermissionsEntity u WHERE u.userRoleEntity.id = :id")
    void deleteAllWithRole(long id);
}
