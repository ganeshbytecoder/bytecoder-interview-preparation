package com.gschoudhary.Bytecoder.questions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends CrudRepository<QuestionsEntity, Long> {
}