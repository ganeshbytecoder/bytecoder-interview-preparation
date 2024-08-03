package com.gschoudhary.Bytecoder.subjects;

import org.springframework.stereotype.Service;

@Service
public class SubjectsServiceImpl implements SubjectsService {


    @Override
    public SubjectsDto create(SubjectsDto subjectsDto) {
        System.out.println(subjectsDto);
        subjectsDto.setExamId(122);
        return subjectsDto;
    }

    @Override
    public SubjectsDto getAll(SubjectsDto subjectsDto) {
        System.out.println(subjectsDto);
        subjectsDto.setExamId(122);
        return subjectsDto;
    }


    @Override
    public SubjectsDto getById(SubjectsDto subjectsDto) {
        System.out.println(subjectsDto);
        subjectsDto.setExamId(122);
        return subjectsDto;
    }


    @Override
    public SubjectsDto update(SubjectsDto subjectsDto) {
        System.out.println(subjectsDto);
        subjectsDto.setExamId(122);
        return subjectsDto;
    }


    @Override
    public SubjectsDto delete(SubjectsDto subjectsDto) {
        System.out.println(subjectsDto);
        subjectsDto.setExamId(122);
        return subjectsDto;
    }
}
