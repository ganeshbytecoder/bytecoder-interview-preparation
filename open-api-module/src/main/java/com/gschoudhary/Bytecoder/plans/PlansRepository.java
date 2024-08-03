package com.gschoudhary.Bytecoder.plans;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepository extends CrudRepository<PlansEntity, Long> {
}