package com.gschoudhary.open2api.restcontroller;

import com.gschoudhary.open2api.dto.UserDto;
import com.gschoudhary.open2api.service.syncApi.OneApiServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Health check rest controller
 */
@RestController
public class TestRestController {
    private final Logger logger;

    private OneApiServiceImp oneApiServiceImp;

    @Autowired
    public TestRestController(OneApiServiceImp oneApiServiceImp) {
        this.oneApiServiceImp = oneApiServiceImp;
        this.logger = LoggerFactory.getLogger(TestRestController.class);
    }

    /**
     * Api to check health of application
     *
     * @return response body
     */
    @PostMapping(path = "/app/test/one-api", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity checkHealth(@RequestBody UserDto userDto) throws IOException {
        logger.info("making request test request ." + userDto.toString());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
