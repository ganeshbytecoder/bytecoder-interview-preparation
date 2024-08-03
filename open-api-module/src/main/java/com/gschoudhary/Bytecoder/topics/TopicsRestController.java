package com.gschoudhary.Bytecoder.topics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/topics")
public class TopicsRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private TopicsService topicsService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) TopicDto topicDto) {
        Object response = topicsService.create(topicDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) TopicDto topicDto) {
        Object response = topicsService.getAll(topicDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) TopicDto topicDto) {
        Object response = topicsService.getById(topicDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) TopicDto topicDto) {
        Object response = topicsService.update(topicDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) TopicDto topicDto) {
        Object response = topicsService.delete(topicDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
