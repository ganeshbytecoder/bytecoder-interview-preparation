package com.gschoudhary.open2api.repository;

import com.gschoudhary.open2api.domain.ApiStatsEntity;
import com.gschoudhary.open2api.domain.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiStatsRepository extends JpaRepository<ApiStatsEntity, Long> {
}
