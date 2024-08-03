package com.gschoudhary.Bytecoder.papers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/papers")
public class PaperRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private PaperService paperService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) PaperDto paperDto) {
        Object response = paperService.create(paperDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) PaperDto paperDto) {
        Object response = paperService.getAll(paperDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) PaperDto paperDto) {
        Object response = paperService.getById(paperDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) PaperDto paperDto) {
        Object response = paperService.update(paperDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) PaperDto paperDto) {
        Object response = paperService.delete(paperDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
