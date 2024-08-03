package com.gschoudhary.Bytecoder.tags;

public interface TagsService {


    TagsDto create(TagsDto tagsDto);

    TagsDto getAll(TagsDto tagsDto);

    TagsDto getById(TagsDto tagsDto);

    TagsDto update(TagsDto tagsDto);

    TagsDto delete(TagsDto tagsDto);
}
