package com.gschoudhary.open2api.repository;

import com.gschoudhary.open2api.domain.ApiConfigEntity;
import com.gschoudhary.open2api.domain.AuthConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthConfigRepository extends JpaRepository<AuthConfigEntity, Long> {
    @Query("select ac from AuthConfigEntity ac where ac.apiCode = :apiCode")
    Optional<AuthConfigEntity> findByApiCode(@Param("apiCode") String authApiCode);
}
