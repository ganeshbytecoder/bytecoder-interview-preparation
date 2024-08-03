package com.gschoudhary.Bytecoder.topics;

import org.springframework.stereotype.Service;

@Service
public class TopicsServiceImpl implements TopicsService {


    @Override
    public TopicDto create(TopicDto topicDto) {
        System.out.println(topicDto);
        topicDto.setExamId(122);
        return topicDto;
    }

    @Override
    public TopicDto getAll(TopicDto topicDto) {
        System.out.println(topicDto);
        topicDto.setExamId(122);
        return topicDto;
    }


    @Override
    public TopicDto getById(TopicDto topicDto) {
        System.out.println(topicDto);
        topicDto.setExamId(122);
        return topicDto;
    }


    @Override
    public TopicDto update(TopicDto topicDto) {
        System.out.println(topicDto);
        topicDto.setExamId(122);
        return topicDto;
    }


    @Override
    public TopicDto delete(TopicDto topicDto) {
        System.out.println(topicDto);
        topicDto.setExamId(122);
        return topicDto;
    }
}
