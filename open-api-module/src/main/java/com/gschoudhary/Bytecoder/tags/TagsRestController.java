package com.gschoudhary.Bytecoder.tags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/tags")
public class TagsRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private TagsService tagsService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) TagsDto tagsDto) {
        Object response = tagsService.create(tagsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) TagsDto tagsDto) {
        Object response = tagsService.getAll(tagsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) TagsDto tagsDto) {
        Object response = tagsService.getById(tagsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) TagsDto tagsDto) {
        Object response = tagsService.update(tagsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) TagsDto tagsDto) {
        Object response = tagsService.delete(tagsDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
