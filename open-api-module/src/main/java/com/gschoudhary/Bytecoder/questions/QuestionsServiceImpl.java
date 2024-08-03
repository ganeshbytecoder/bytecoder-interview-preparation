package com.gschoudhary.Bytecoder.questions;

import org.springframework.stereotype.Service;

@Service
public class QuestionsServiceImpl implements QuestionsService {


    @Override
    public QuestionsDto create(QuestionsDto questionsDto) {
        System.out.println(questionsDto);
        questionsDto.setExamId(122);
        return questionsDto;
    }

    @Override
    public QuestionsDto getAll(QuestionsDto questionsDto) {
        System.out.println(questionsDto);
        questionsDto.setExamId(122);
        return questionsDto;
    }


    @Override
    public QuestionsDto getById(QuestionsDto questionsDto) {
        System.out.println(questionsDto);
        questionsDto.setExamId(122);
        return questionsDto;
    }


    @Override
    public QuestionsDto update(QuestionsDto questionsDto) {
        System.out.println(questionsDto);
        questionsDto.setExamId(122);
        return questionsDto;
    }


    @Override
    public QuestionsDto delete(QuestionsDto questionsDto) {
        System.out.println(questionsDto);
        questionsDto.setExamId(122);
        return questionsDto;
    }
}
