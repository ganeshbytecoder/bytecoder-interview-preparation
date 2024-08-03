package com.gschoudhary.Bytecoder.papers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends CrudRepository<PaperEntity, Long> {
}