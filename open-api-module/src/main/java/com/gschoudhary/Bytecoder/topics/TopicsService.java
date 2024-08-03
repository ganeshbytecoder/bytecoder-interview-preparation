package com.gschoudhary.Bytecoder.topics;

public interface TopicsService {


    TopicDto create(TopicDto topicDto);

    TopicDto getAll(TopicDto topicDto);

    TopicDto getById(TopicDto topicDto);

    TopicDto update(TopicDto topicDto);

    TopicDto delete(TopicDto topicDto);
}
