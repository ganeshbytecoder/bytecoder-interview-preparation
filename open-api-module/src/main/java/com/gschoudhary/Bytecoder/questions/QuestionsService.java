package com.gschoudhary.Bytecoder.questions;

public interface QuestionsService {


    QuestionsDto create(QuestionsDto questionsDto);

    QuestionsDto getAll(QuestionsDto questionsDto);

    QuestionsDto getById(QuestionsDto questionsDto);

    QuestionsDto update(QuestionsDto questionsDto);

    QuestionsDto delete(QuestionsDto questionsDto);
}
