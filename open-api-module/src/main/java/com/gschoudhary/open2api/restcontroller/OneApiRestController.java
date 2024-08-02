package com.gschoudhary.open2api.restcontroller;

import com.gschoudhary.Users.UserDto;
import com.gschoudhary.open2api.service.OneApiServiceImp;
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

import javax.validation.ValidationException;

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
    JsonMapper jsonMapper;

    @Autowired
    ServiceFactory serviceFactory;

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
    @PostMapping(path = "/api/v1/one-api", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestHeader(name = "code", defaultValue = "CODE") String code, @RequestBody(required = true) String object) {
        logger.info("making request =" + code + " " + object);

        Object response = null;
        logger.info("current thread in controller" + Thread.currentThread());
        if (!jsonValidator.isValidJson(object)) {
            return new ResponseEntity<>("object is not json. please make right request", HttpStatus.OK);
        }
        try {

            UserDto userDto = jsonMapper.fromJson(object, UserDto.class);

            response = serviceFactory.getService(code).apply(userDto);
        } catch (Exception e) {
            throw new ValidationException(e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
