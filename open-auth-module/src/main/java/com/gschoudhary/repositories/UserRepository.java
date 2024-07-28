package com.gschoudhary.repositories;


import com.gschoudhary.helpers.RefreshableCRUDRepository;
import com.gschoudhary.models.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserInfo, Long> {

   public UserInfo findByUsername(String username);

   UserInfo findFirstById(Long id);

}
