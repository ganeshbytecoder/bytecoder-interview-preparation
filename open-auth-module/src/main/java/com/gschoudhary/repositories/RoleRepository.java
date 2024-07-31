package com.gschoudhary.repositories;

import com.gschoudhary.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

        Optional<RoleEntity> findByTitle(String title);
}
