package com.gschoudhary.Bytecoder.subjects;

public interface SubjectsService {


    SubjectsDto create(SubjectsDto subjectsDto);

    SubjectsDto getAll(SubjectsDto subjectsDto);

    SubjectsDto getById(SubjectsDto subjectsDto);

    SubjectsDto update(SubjectsDto subjectsDto);

    SubjectsDto delete(SubjectsDto subjectsDto);
}
