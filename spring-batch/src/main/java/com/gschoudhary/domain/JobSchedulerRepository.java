package com.gschoudhary.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSchedulerRepository extends JpaRepository<JobSchedulerEntity, Long> {
}
