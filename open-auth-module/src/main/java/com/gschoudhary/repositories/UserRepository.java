package com.gschoudhary.repositories;


import com.gschoudhary.helpers.RefreshableCRUDRepository;
import com.gschoudhary.models.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserEntity, Long> {

   UserEntity findByUsername(String username);

   UserEntity findFirstById(Long id);

}
