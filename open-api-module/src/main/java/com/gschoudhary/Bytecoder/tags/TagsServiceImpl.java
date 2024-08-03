package com.gschoudhary.Bytecoder.tags;

import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl implements TagsService {


    @Override
    public TagsDto create(TagsDto tagsDto) {
        System.out.println(tagsDto);
        tagsDto.setExamId(122);
        return tagsDto;
    }

    @Override
    public TagsDto getAll(TagsDto tagsDto) {
        System.out.println(tagsDto);
        tagsDto.setExamId(122);
        return tagsDto;
    }


    @Override
    public TagsDto getById(TagsDto tagsDto) {
        System.out.println(tagsDto);
        tagsDto.setExamId(122);
        return tagsDto;
    }


    @Override
    public TagsDto update(TagsDto tagsDto) {
        System.out.println(tagsDto);
        tagsDto.setExamId(122);
        return tagsDto;
    }


    @Override
    public TagsDto delete(TagsDto tagsDto) {
        System.out.println(tagsDto);
        tagsDto.setExamId(122);
        return tagsDto;
    }
}
