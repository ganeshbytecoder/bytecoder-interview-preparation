package com.gschoudhary.Bytecoder.exams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/exams")
public class ExamRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private ExamsService examsService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) ExamDtos examDtos) {
        Object response = examsService.create(examDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) ExamDtos examDtos) {
        Object response = examsService.getAll(examDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) ExamDtos examDtos) {
        Object response = examsService.getById(examDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) ExamDtos examDtos) {
        Object response = examsService.update(examDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) ExamDtos examDtos) {
        Object response = examsService.delete(examDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
