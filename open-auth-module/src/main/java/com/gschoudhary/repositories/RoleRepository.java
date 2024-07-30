package com.gschoudhary.repositories;

import com.gschoudhary.models.RefreshToken;
import com.gschoudhary.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, Integer> {

        Optional<UserRole> findByName(String name);
}
