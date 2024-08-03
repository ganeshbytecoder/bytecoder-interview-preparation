package com.gschoudhary.Bytecoder.exams;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CrudRepository<ExamEntity, Long> {
}