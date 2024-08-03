package com.gschoudhary.Bytecoder.plans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/plans")
public class PlansRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private PlansService plansService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) PlansDto plansDto) {
        Object response = plansService.create(plansDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) PlansDto plansDto) {
        Object response = plansService.getAll(plansDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) PlansDto plansDto) {
        Object response = plansService.getById(plansDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) PlansDto plansDto) {
        Object response = plansService.update(plansDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) PlansDto plansDto) {
        Object response = plansService.delete(plansDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
