package com.gschoudhary.Bytecoder.exams;

import org.springframework.stereotype.Service;

@Service
public class ExamsServiceImpl {


    public ExamDtos addExam(ExamDtos examDtos){
        System.out.println(examDtos);
        examDtos.setExamId(122);
        return  examDtos;
    }
}
