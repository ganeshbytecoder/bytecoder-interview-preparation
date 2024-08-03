package com.gschoudhary.Bytecoder.questions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/questions")
public class QuestionsRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private QuestionsService questionsService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) QuestionsDto questionsDto) {
        Object response = questionsService.create(questionsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) QuestionsDto questionsDto) {
        Object response = questionsService.getAll(questionsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) QuestionsDto questionsDto) {
        Object response = questionsService.getById(questionsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) QuestionsDto questionsDto) {
        Object response = questionsService.update(questionsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) QuestionsDto questionsDto) {
        Object response = questionsService.delete(questionsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
