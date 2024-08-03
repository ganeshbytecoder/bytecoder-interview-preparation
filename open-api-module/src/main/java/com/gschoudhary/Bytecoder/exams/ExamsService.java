package com.gschoudhary.Bytecoder.exams;

public interface ExamsService {


    ExamDtos create(ExamDtos examDtos);

    ExamDtos getAll(ExamDtos examDtos);

    ExamDtos getById(ExamDtos examDtos);

    ExamDtos update(ExamDtos examDtos);

    ExamDtos delete(ExamDtos examDtos);
}
