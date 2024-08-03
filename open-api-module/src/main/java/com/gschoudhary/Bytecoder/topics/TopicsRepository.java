package com.gschoudhary.Bytecoder.topics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends CrudRepository<TopicsEntity, Long> {
}