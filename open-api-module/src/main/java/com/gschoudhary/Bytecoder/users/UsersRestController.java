package com.gschoudhary.Bytecoder.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v2/users")
public class UsersRestController {
    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private UsersService usersService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody(required = true) UserDto userDto) {
        Object response = usersService.create(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestBody(required = true) UserDto userDto) {
        Object response = usersService.getAll(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@RequestBody(required = true) UserDto userDto) {
        Object response = usersService.getById(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody(required = true) UserDto userDto) {
        Object response = usersService.update(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody(required = true) UserDto userDto) {
        Object response = usersService.delete(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
