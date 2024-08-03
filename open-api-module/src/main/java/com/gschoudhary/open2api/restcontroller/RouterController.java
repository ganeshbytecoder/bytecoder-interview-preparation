package com.gschoudhary.open2api.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
public class RouterController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);


    @PostMapping(path = "/{api_code}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@PathVariable("api_code") String api_code, @RequestHeader(name = "code", defaultValue = "CODE") String code, @RequestBody(required = true) String object, HttpServletRequest request
    ) {
        logger.info("making request =" + code + " " + object);

        Object response = null;

        try {
            String pathUri = request.getRequestURI();
            System.out.println("Api code " + pathUri);
            response = Router.post.get(pathUri).apply(object);


        } catch (Exception e) {
            throw new ValidationException(e);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
