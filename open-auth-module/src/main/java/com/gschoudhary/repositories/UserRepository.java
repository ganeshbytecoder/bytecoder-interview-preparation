package com.gschoudhary.repositories;


import com.gschoudhary.helpers.RefreshableCRUDRepository;
import com.gschoudhary.models.UserInfoEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserInfoEntity, Long> {

   public UserInfoEntity findByUsername(String username);

   UserInfoEntity findFirstById(Long id);

}
