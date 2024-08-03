package com.gschoudhary.Bytecoder.subjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2")
public class SubjectsRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private SubjectsService subjectsService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) SubjectsDto subjectsDto) {
        Object response = subjectsService.create(subjectsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) SubjectsDto subjectsDto) {
        Object response = subjectsService.getAll(subjectsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) SubjectsDto subjectsDto) {
        Object response = subjectsService.getById(subjectsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) SubjectsDto subjectsDto) {
        Object response = subjectsService.update(subjectsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) SubjectsDto subjectsDto) {
        Object response = subjectsService.delete(subjectsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
