package com.gschoudhary.open2api.restcontroller;

import com.gschoudhary.open2api.service.syncApi.OneApiServiceImp;
import com.gschoudhary.open2api.utils.validator.JsonValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Health check rest controller
 */
@RestController
public class OneApiRestController {
    private final Logger logger;

    private OneApiServiceImp oneApiServiceImp;

    private JsonValidator jsonValidator;

    @Autowired
    public OneApiRestController(OneApiServiceImp oneApiServiceImp, JsonValidator jsonValidator) {
        this.oneApiServiceImp = oneApiServiceImp;
        this.jsonValidator = jsonValidator;
        this.logger = LoggerFactory.getLogger(OneApiRestController.class);
    }

    /**
     * Api to check health of application
     *
     * @return response body
     */
    @PostMapping(path = "/app/v1/one-api", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity checkHealth(@RequestHeader(name = "code", defaultValue = "CODE") String code, @RequestHeader(name = "type", defaultValue = "SYNC") String requestType, @RequestBody String object) throws ExecutionException, InterruptedException {
        logger.info("making request =" + code + " " + object);
        String response = null;
        logger.info("current thread in controller" + Thread.currentThread());
        if (!jsonValidator.isValidJson(object)) {
            return new ResponseEntity<>("object is not json. please make right request", HttpStatus.OK);
        }
        response = oneApiServiceImp.makeRequest(code, object, requestType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
