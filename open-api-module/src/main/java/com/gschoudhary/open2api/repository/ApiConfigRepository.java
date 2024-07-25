package com.gschoudhary.open2api.repository;

import com.gschoudhary.open2api.domain.ApiConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiConfigRepository extends JpaRepository<ApiConfigEntity, Long> {
    @Query("select ac from ApiConfigEntity ac where ac.apiUniqueCode = :apiUniqueCode")
    Optional<ApiConfigEntity> findByApiCode(@Param("apiUniqueCode") String apiCode);
}
