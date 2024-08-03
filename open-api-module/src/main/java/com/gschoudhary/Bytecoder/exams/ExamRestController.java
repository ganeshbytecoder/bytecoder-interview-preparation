package com.gschoudhary.Bytecoder.exams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2")
public class ExamRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private ExamsServiceImpl examsService;

    @PostMapping(path = "/exams", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody(required = true) ExamDtos examDtos) {

        Object response = examsService.addExam(examDtos);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
