package com.gschoudhary.Bytecoder.comments;

public interface CommentService {


    CommentDto create(CommentDto commentDto);

    CommentDto getAll(CommentDto commentDto);

    CommentDto getById(CommentDto commentDto);

    CommentDto update(CommentDto commentDto);

    CommentDto delete(CommentDto commentDto);
}
