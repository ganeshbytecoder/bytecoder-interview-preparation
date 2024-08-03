package com.gschoudhary.Bytecoder.subjects;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends CrudRepository<SubjectsEntity, Long> {
}