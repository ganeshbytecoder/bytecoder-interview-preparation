package com.gschoudhary.Bytecoder.papers;

import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService {


    @Override
    public PaperDto create(PaperDto paperDto) {
        System.out.println(paperDto);
        paperDto.setExamId(122);
        return paperDto;
    }

    @Override
    public PaperDto getAll(PaperDto paperDto) {
        System.out.println(paperDto);
        paperDto.setExamId(122);
        return paperDto;
    }


    @Override
    public PaperDto getById(PaperDto paperDto) {
        System.out.println(paperDto);
        paperDto.setExamId(122);
        return paperDto;
    }


    @Override
    public PaperDto update(PaperDto paperDto) {
        System.out.println(paperDto);
        paperDto.setExamId(122);
        return paperDto;
    }


    @Override
    public PaperDto delete(PaperDto paperDto) {
        System.out.println(paperDto);
        paperDto.setExamId(122);
        return paperDto;
    }
}
