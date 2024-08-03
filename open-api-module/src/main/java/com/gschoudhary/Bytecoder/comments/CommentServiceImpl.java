package com.gschoudhary.Bytecoder.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {


    @Override
    public CommentDto create(CommentDto commentDto) {
        System.out.println(commentDto);
        commentDto.setExamId(122);
        return commentDto;
    }

    @Override
    public CommentDto getAll(CommentDto commentDto) {
        System.out.println(commentDto);
        commentDto.setExamId(122);
        return commentDto;
    }


    @Override
    public CommentDto getById(CommentDto commentDto) {
        System.out.println(commentDto);
        commentDto.setExamId(122);
        return commentDto;
    }


    @Override
    public CommentDto update(CommentDto commentDto) {
        System.out.println(commentDto);
        commentDto.setExamId(122);
        return commentDto;
    }


    @Override
    public CommentDto delete(CommentDto commentDto) {
        System.out.println(commentDto);
        commentDto.setExamId(122);
        return commentDto;
    }
}
