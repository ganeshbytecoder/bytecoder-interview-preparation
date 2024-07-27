package com.gschoudhary.controller;

import com.gschoudhary.constant.ApiConstants;
import com.gschoudhary.constant.RequestMessageConstants;
import com.gschoudhary.domain.BookEntity;
import com.gschoudhary.domain.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Health check rest controller
 */
@RestController
@Api(value = "HealthCheck")
public class HealthCheckRestController {
    private final Logger logger;
    private BookRepository bookRepository;
    @Autowired
    public HealthCheckRestController(BookRepository bookRepository) {
        this.bookRepository= bookRepository;
        this.logger = LoggerFactory.getLogger(HealthCheckRestController.class);
    }



    /**
     * Api to check health of application
     *
     * @return response body
     */
    @ApiOperation(value = "Health check api")
    @GetMapping(path = ApiConstants.HEALTH_CHECK_API, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity checkHealth() {
        logger.info("Health checking.");

        BookEntity bookEntity = BookEntity.builder()
                .bookAuthor("bookRecord.getBookAuthor()")
                .bookFormat("bookRecord.getBookFormat()")

                .build();
        bookEntity=bookRepository.save(bookEntity);
        System.out.println(bookEntity.getBookName());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", RequestMessageConstants.HEALTH_CHECK_MESSAGE.getMessage());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
