package com.gschoudhary.Bytecoder.exams;

import org.springframework.stereotype.Service;

@Service
public class ExamsServiceImpl implements ExamsService {


    @Override
    public ExamDtos create(ExamDtos examDtos) {
        System.out.println(examDtos);
        examDtos.setExamId(122);
        return examDtos;
    }

    @Override
    public ExamDtos getAll(ExamDtos examDtos) {
        System.out.println(examDtos);
        examDtos.setExamId(122);
        return examDtos;
    }


    @Override
    public ExamDtos getById(ExamDtos examDtos) {
        System.out.println(examDtos);
        examDtos.setExamId(122);
        return examDtos;
    }


    @Override
    public ExamDtos update(ExamDtos examDtos) {
        System.out.println(examDtos);
        examDtos.setExamId(122);
        return examDtos;
    }


    @Override
    public ExamDtos delete(ExamDtos examDtos) {
        System.out.println(examDtos);
        examDtos.setExamId(122);
        return examDtos;
    }
}
