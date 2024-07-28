package com.gschoudhary.controllers;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class HealthCheckRestController {
    private final Logger logger;

    @Autowired
    public HealthCheckRestController() {
        this.logger = LoggerFactory.getLogger("HealthCheckRestController");
    }

    /**
     * Api to check health of application
     *
     * @return response body
     */
    @GetMapping(path = "/app/health", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity checkHealth() {
        log.info("testing slf4j");
        logger.info("Health checking.");
        logger.trace("A TRACE Health checking");
        logger.debug("A DEBUG Health checking");
        logger.info("An INFO Health checking");
        logger.warn("A WARN Health checking");
        logger.error("An ERROR Health checking");
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "OK ");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
